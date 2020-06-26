import spatial.dsl._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random
import spatial.lib.metaprogramming._
import java.io._

// TODO: Not sure if SIMD would help much for this app.
//  However, it is still better than platforms such as TPU or Brainwave, as those cannot even map trees spatially.
//  The ideal way is to fetch the pre-trained model to reconstruct the app.
//  For now, it takes a bit too much of effort to parse the tree structure encoded by JPMML format
//  In addition, this doesn't help us understand if Plasticine can handle this sort of application properly.
//  We can achieve the same goal by randomly generating some decision trees and run it...

class RandomCARTInference_0 extends RandomCARTInference
//class RandomCARTInference_1 extends RandomCARTInference(nEstimators=8, nFeatures=128, fp=4, nSamplesPerTile=16,maxSplits=128,maxDepth=80)
//class RandomCARTInference_final extends RandomCARTInference(nEstimators=8, nFeatures=128, fp=4, nSamplesPerTile=16,maxSplits=128,maxDepth=80)

@spatial abstract class RandomCARTInference(
    nEstimators: scala.Int = 8,
    maxDepth: scala.Int = 4,
    maxSplits: scala.Int = 2,
    nSamples: scala.Int = 512,
    seed: scala.Int = 1,
    splitTreeProb: scala.Double = 0.7,
    maxSampleVal: scala.Int = 64,
    maxLeafVal: scala.Int = 20,
    maxFeatureVal: scala.Int = 10,
    nFeatures: scala.Int = 32,
    nSamplesPerTile: scala.Int = 16,
    ip:scala.Int = 16,
    fp: scala.Int = 1, // Number of parallel features
    np: scala.Int = 1, // Number of parallel tiles
    fifoLen: scala.Int = 16,
    serialize: scala.Boolean = false,
    serializeFilePath: java.lang.String = "./tree.serialized"
) extends SpatialTest with MetaProgramming {

  type F = Fix[TRUE,_16,_16]
  type I = Int
  type sInt = scala.Int
  type sDouble = scala.Double
  type sArray[_] = scala.Array[_]

  val randomGen: Random =
    IfElse(seed == -1)(new scala.util.Random())(new scala.util.Random(seed))
  sealed trait Tree[+A]
  case class Leaf[A](value: A) extends Tree[A]
  case class Split[A](
      left: Tree[A],
      right: Tree[A],
      conditionThreshold: A
  ) extends Tree[A]

  def randomTreeGenerator[A](
      depth: sInt = maxDepth,
      maxSplits: sInt = maxSplits,
      leafValGenerator: () => A,
      thresholdGenerator: () => A,
      splitProb: sDouble = 0.5): (Tree[A], ListBuffer[Split[A]]) = {

    var nLeftSplits: sInt = maxSplits
    var featureListBuf: mutable.ListBuffer[Split[A]] =
      mutable.ListBuffer[Split[A]]()
    def generate(p: sDouble = 0.5): Tree[A] = {
      val pn = randomGen.nextDouble
      IfElse(nLeftSplits > 0 && pn < p) {
        nLeftSplits = nLeftSplits - 1
        val r = Split(generate(p), generate(p), thresholdGenerator())
        featureListBuf += r
        r.asInstanceOf[Tree[A]]
      } {
        Leaf(leafValGenerator()).asInstanceOf[Tree[A]]
      }
    }

    val tree = generate(splitProb)
    (tree, featureListBuf)
  }


  def main(args: Array[String]): Unit = {
    // TODO: do the tree generation here, and then transform it into Spatial type within Accel.
    //  This way we avoid transformation in the function body.
    val (treeList, treeFeatureList) = List
      .tabulate(nEstimators)(_ => {
        val leafGenerator: () => sDouble =
          () => randomGen.nextDouble * maxLeafVal.toDouble
        val splitGenerator: () => sDouble =
          () => randomGen.nextDouble * maxFeatureVal.toDouble
        randomTreeGenerator[sDouble](
          maxDepth,
          maxSplits,
          leafGenerator,
          splitGenerator,
          splitProb = splitTreeProb
        )
      })
      .unzip

    val samples =
      Matrix.tabulate(nFeatures.to[I32], nSamples.to[I32])((_, _) =>
        (randomGen.nextDouble * maxLeafVal.toDouble).to[F])

    val split2FeatureIdxMaps = treeFeatureList.map(
      treeFeatures =>
        Map(
          treeFeatures zip Random
            .shuffle(List.tabulate(nFeatures)(i => i))
            .take(treeFeatures.length): _*)
    )

    // We assume that samples come in in a streaming fashion.
    val dataSrc = DRAM[F](nFeatures.to[I32], nSamples.to[I32])
    val predDest = DRAM[F](nSamples.to[I32])
    setMem(dataSrc, samples)

    val featureIndicesList = split2FeatureIdxMaps.map { m =>
      m.values.toList.map(e => e)
    }

    treeList.foreach(tr => scala.Console.println(tr))

    sealed trait TreeSerialized[+sDouble]
    case class LeafSerialized[sDouble](value: sDouble) extends TreeSerialized[sDouble]
    case class SplitSerialized[sDouble](
        left: TreeSerialized[sDouble],
        right: TreeSerialized[sDouble],
        conditionThreshold: sDouble,
        featureIdxMapping: sInt
    ) extends TreeSerialized[sDouble]

    (treeList zip split2FeatureIdxMaps).foreach { case (tp, trMap) =>
      def convertTreeListMapToIdxTree(tr: Tree[sDouble]): TreeSerialized[sDouble] = {
        tr match {
          case Split(left, right, conditionThreshold) =>
            SplitSerialized(
              convertTreeListMapToIdxTree(left),
              convertTreeListMapToIdxTree(right),
              conditionThreshold,
              trMap(tr.asInstanceOf[Split[sDouble]])
            )
          case Leaf(value) => LeafSerialized(value = value)
        }
      }

      val serializedTree = convertTreeListMapToIdxTree(tp)
      IfElse(cond = serialize) {
        scala.Console.println("=== feature tree ===:")
        scala.Console.print("(")
        scala.Console.print(serializedTree)
        scala.Console.println(")")
      } {}
    }


    Accel {
      Foreach(nSamples by nSamplesPerTile par np) { iTile =>
        val fRegMaps = featureIndicesList.map(features =>
          Map(features.map(f => (f, FIFO[F](fifoLen.to[I32]))): _*))

        ForeachWithLane(nFeatures by 1 par fp) { case (iFeature,lane) =>
          val inMem = FIFO[F](fifoLen)
          inMem load dataSrc(iFeature, iTile :: iTile + nSamplesPerTile par ip)

          Foreach(nSamplesPerTile by 1 par ip) { idxInTile =>
            val feature = inMem.deq
            fRegMaps.foreach { regMap => // Unrolled by nTrees
              regMap.foreach {
                case (featureIdx: scala.Int, r: FIFO[F]) =>
                  val currFeatureVal = feature
                  val currLane = (featureIdx % fp) == lane
                  r.enq(currFeatureVal, featureIdx.to[I32] == iFeature && currLane)
              }
            }
          }
        }

        val outMem = FIFO[F](fifoLen)
        Foreach(nSamplesPerTile by 1 par ip) { idxInTile =>
          val treeResults =
            (treeList zip split2FeatureIdxMaps zip fRegMaps).map { tup =>
              val ((tree, split2FeatureIdxMap), treeRegs) = tup
              def traverse(t: Tree[sDouble]): F = {
                t match {
                  case Split(leftTree, rightTree, thresh) =>
                    val featureIdx =
                      split2FeatureIdxMap(t.asInstanceOf[Split[sDouble]])
                    val feature = treeRegs(featureIdx).deq()
                    val leftTraverseVal = traverse(leftTree)
                    val rightTraverseVal = traverse(rightTree)
                    mux(feature < thresh.toUnchecked[F],
                        leftTraverseVal.to[F],
                        rightTraverseVal.to[F])
                  case Leaf(value) => value.toUnchecked[F]
                }
              }

              traverse(tree)
            }
          outMem.enq(treeResults.sumTree / nEstimators)
        }

        predDest(iTile :: iTile + nSamplesPerTile par ip) store outMem
      }
    }

    val accelScores = getMem(predDest)
    val goldScores = Array.tabulate(nSamples) { iSample =>
      (treeList zip split2FeatureIdxMaps).map {
        tup =>
          val (tree, split2FeatureIdxMap) = tup
          def traverse(t: Tree[sDouble]): F = {
            t match {
              case Leaf(v) => v.toUnchecked[F]
              case Split(lt, rt, th) =>
                val iFt = split2FeatureIdxMap(t.asInstanceOf[Split[sDouble]])
                val ft = samples(iFt.to[I32], iSample.to[I32])
                if (ft < th.toUnchecked[F]) traverse(lt) else traverse(rt)
            }
          }

          traverse(tree)
      }.sumTree / nEstimators.to[F]
    }

    val cksum = accelScores
      .zip(goldScores) { (a, b) =>
        a == b
      }
      .reduce(_ && _)

    //printMatrix(samples, "samples = ")
    //writeCSV2D(samples, "sample.csv")

    printArray(accelScores, "prediction scores = ")
    printArray(goldScores, "gold scores = ")
    println("PASS: " + cksum + "(RandomCARTInference)")
    assert(cksum)
  }
}
