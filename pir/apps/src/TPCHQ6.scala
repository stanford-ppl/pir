import pir._
import pir.node._
import arch._
import prism.enums._

object TPCHQ6 extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    def withCtx[T<:IR](x:T, ctx:String):T = { srcCtxOf(x) = ctx; x}
    def withAcc[T<:Memory](x:T, accum:Boolean) = { isAccum(x) = accum; x }
    def withBD[T<:Memory](x:T, depth:Int) = { bufferDepthOf(x) = depth; x }
    def withCount[T](x:T, count:Long) = { countOf(x) = Some(count); x }
    def withDims[T<:Memory](x:T, dims:List[Int]) = { staticDimsOf(x) = dims; x }
    def withBound[T<:Memory](x:T, bound:Any) = { boundOf(x) = bound; x }
    def withFile[T<:Memory](x:T, path:String) = { fileNameOf(x) = path; x }
    def withDeps(x:PIRNode, deps:List[PIRNode]) = { antiDepsOf(x) = deps; x }
    val nameSpace = scala.collection.mutable.Map[String,Any]()
    def lookup[T](name:String) = nameSpace(name).asInstanceOf[T]
    def withName[T<:IR](x:T, name:String):T = { if (!nameOf.contains(x)) nameOf(x) = name; nameSpace += name -> x; x}
    def split1 = {
    // DefRhs(LhsMem(x2113,0,None),ArgIn,WrappedArray((init,0)))
    val x2113 = withCtrl(design.top.topController) { withBound(withAcc(withBD(withName(withCtx(ArgIn(init=0), "TPCHQ6.scala:23:25:dataSize"), "x2113"), 1), false), 1024) } // ArgInNew(Const(0))
    // DefRhs(LhsSym(x2115,None),ReadMem,WrappedArray(LhsMem(x2113,0,None)))
    val x2115 = withCtrl(design.top.topController) { withName(withCtx(ReadMem(x2113), "TPCHQ6.scala:26:28"), "x2115") } // RegRead(x2113)
    // DefRhs(LhsSym(x2116,None),DRAM,WrappedArray((dims,List(x2115))))
    val x2116 = withCtrl(design.top.topController) { withName(withCtx(DRAM(dims=List(x2115)), "TPCHQ6.scala:26:27:dates"), "x2116") } // DRAMNew(ArrayBuffer(x2115),Const(0))
    // DefRhs(LhsSym(x2117,None),ReadMem,WrappedArray(LhsMem(x2113,0,None)))
    val x2117 = withCtrl(design.top.topController) { withName(withCtx(ReadMem(x2113), "TPCHQ6.scala:27:28"), "x2117") } // RegRead(x2113)
    // DefRhs(LhsSym(x2118,None),DRAM,WrappedArray((dims,List(x2117))))
    val x2118 = withCtrl(design.top.topController) { withName(withCtx(DRAM(dims=List(x2117)), "TPCHQ6.scala:27:27:quants"), "x2118") } // DRAMNew(ArrayBuffer(x2117),Const(0))
    // DefRhs(LhsSym(x2119,None),ReadMem,WrappedArray(LhsMem(x2113,0,None)))
    }; split1
    def split2 = {
    val x2119 = withCtrl(design.top.topController) { withName(withCtx(ReadMem(lookup[ArgIn]("x2113")), "TPCHQ6.scala:28:26"), "x2119") } // RegRead(x2113)
    // DefRhs(LhsSym(x2120,None),DRAM,WrappedArray((dims,List(x2119))))
    val x2120 = withCtrl(design.top.topController) { withName(withCtx(DRAM(dims=List(x2119)), "TPCHQ6.scala:28:25:discts"), "x2120") } // DRAMNew(ArrayBuffer(x2119),Const(0))
    // DefRhs(LhsSym(x2121,None),ReadMem,WrappedArray(LhsMem(x2113,0,None)))
    val x2121 = withCtrl(design.top.topController) { withName(withCtx(ReadMem(lookup[ArgIn]("x2113")), "TPCHQ6.scala:29:26"), "x2121") } // RegRead(x2113)
    // DefRhs(LhsSym(x2122,None),DRAM,WrappedArray((dims,List(x2121))))
    val x2122 = withCtrl(design.top.topController) { withName(withCtx(DRAM(dims=List(x2121)), "TPCHQ6.scala:29:25:prices"), "x2122") } // DRAMNew(ArrayBuffer(x2121),Const(0))
    // DefRhs(LhsMem(x2123,0,None),ArgOut,WrappedArray((init,0)))
    val x2123 = withCtrl(design.top.topController) { withAcc(withBD(withName(withCtx(ArgOut(init=0), "TPCHQ6.scala:32:21:out"), "x2123"), 1), false) } // ArgOutNew(Const(0))
    // DefRhs(LhsSym(x2253,None),UnitController,WrappedArray((style,SeqPipe), (level,OuterControl)))
    val x2253 = withCtrl(design.top.topController) { withName(withCtx(UnitController(style=SeqPipe,level=OuterControl), "TPCHQ6.scala:39:11"), "x2253") } // Hwblock(Block(Const(())),false)
    }; split2
    def split3 = {
    // DefRhs(LhsMem(x2128,0,None),Reg,WrappedArray((init,Some(0))))
    val x2128_d0 = withCtrl(lookup[UnitController]("x2253")) { withAcc(withBD(withName(withCtx(Reg(init=Some(0)), "TPCHQ6.scala:43:20:acc"), "x2128_d0"), 1), false) } // RegNew(Const(0))
    // DefRhs(LhsMem(x2128,1,None),Reg,WrappedArray((init,Some(0))))
    val x2128_d1 = withCtrl(lookup[UnitController]("x2253")) { withAcc(withBD(withName(withCtx(Reg(init=Some(0)), "TPCHQ6.scala:43:20:acc"), "x2128_d1"), 1), true) } // RegNew(Const(0))
    // DefRhs(LhsSym(x2129,None),ReadMem,WrappedArray(LhsMem(x2113,0,None)))
    val x2129 = withCtrl(lookup[UnitController]("x2253")) { withName(withCtx(ReadMem(lookup[ArgIn]("x2113")), "TPCHQ6.scala:44:19"), "x2129") } // RegRead(x2113)
    // DefRhs(LhsSym(x2130,None),Counter,WrappedArray((min,Const(0)), (max,x2129), (step,Const(32)), (par,1)))
    val x2130 = withCtrl(lookup[UnitController]("x2253")) { withName(withCtx(Counter(min=Const(0),max=x2129,step=Const(32),par=1), "TPCHQ6.scala:44:34"), "x2130") } // CounterNew(Const(0),x2129,Const(32),Const(1))
    // DefRhs(LhsSym(x2131,None),CounterChain,WrappedArray(List(x2130)))
    val x2131 = withCtrl(lookup[UnitController]("x2253")) { withName(withCtx(CounterChain(List(x2130)), "TPCHQ6.scala:63:8"), "x2131") } // CounterChainNew(List(x2130))
    // DefRhs(LhsSym(x2249,None),LoopController,WrappedArray((style,MetaPipe), (level,OuterControl), (cchain,x2131)))
    }; split3
    def split4 = {
    val x2249 = withCtrl(lookup[UnitController]("x2253")) { withName(withCtx(LoopController(style=MetaPipe,level=OuterControl,cchain=lookup[CounterChain]("x2131")), "TPCHQ6.scala:63:8"), "x2249") } // UnrolledReduce(List(Const(true)),x2131,x2128,Block((x2128) => Const(())),List(List(b1269)),List(List(b1270)))
    // DefRhs(LhsSym(b1269,None),CounterIter,WrappedArray(x2130, Some(0)))
    val b1269 = withCtrl(lookup[LoopController]("x2249")) { withName(CounterIter(lookup[Counter]("x2130"),Some(0)), "b1269") } // 
    // DefRhs(LhsSym(b1270,None),Const[Boolean],WrappedArray(true))
    val b1270 = withCtrl(lookup[LoopController]("x2249")) { withName(Const[Boolean](true), "b1270") } // 
    // DefRhs(LhsMem(x2132,0,Some(0)),SRAM,WrappedArray((size,32), (banking,Strided(banks=16, stride=1))))
    val x2132_d0_b0 = withCtrl(lookup[LoopController]("x2249")) { withDims(withAcc(withBD(withName(withCtx(SRAM(size=32,banking=Strided(banks=16, stride=1)), "TPCHQ6.scala:45:35:datesTile"), "x2132_d0_b0"), 2), false), List(32)) } // SRAMNew(ArrayBuffer(Const(32)))
    // DefRhs(LhsMem(x2133,0,Some(0)),SRAM,WrappedArray((size,32), (banking,Strided(banks=16, stride=1))))
    val x2133_d0_b0 = withCtrl(lookup[LoopController]("x2249")) { withDims(withAcc(withBD(withName(withCtx(SRAM(size=32,banking=Strided(banks=16, stride=1)), "TPCHQ6.scala:46:35:quantsTile"), "x2133_d0_b0"), 2), false), List(32)) } // SRAMNew(ArrayBuffer(Const(32)))
    // DefRhs(LhsMem(x2134,0,Some(0)),SRAM,WrappedArray((size,32), (banking,Strided(banks=16, stride=1))))
    val x2134_d0_b0 = withCtrl(lookup[LoopController]("x2249")) { withDims(withAcc(withBD(withName(withCtx(SRAM(size=32,banking=Strided(banks=16, stride=1)), "TPCHQ6.scala:47:33:disctsTile"), "x2134_d0_b0"), 2), false), List(32)) } // SRAMNew(ArrayBuffer(Const(32)))
    }; split4
    def split5 = {
    // DefRhs(LhsMem(x2135,0,Some(0)),SRAM,WrappedArray((size,32), (banking,Strided(banks=16, stride=1))))
    val x2135_d0_b0 = withCtrl(lookup[LoopController]("x2249")) { withDims(withAcc(withBD(withName(withCtx(SRAM(size=32,banking=Strided(banks=16, stride=1)), "TPCHQ6.scala:48:33:pricesTile"), "x2135_d0_b0"), 2), false), List(32)) } // SRAMNew(ArrayBuffer(Const(32)))
    // DefRhs(LhsSym(x2214,None),UnitController,WrappedArray((style,ForkJoin), (level,OuterControl)))
    val x2214 = withCtrl(lookup[LoopController]("x2249")) { withName(withCtx(UnitController(style=ForkJoin,level=OuterControl), "TPCHQ6.scala:49:18"), "x2214") } // ParallelPipe(List(b1270),Block(Const(())))
    // DefRhs(LhsSym(x2137,None),UnitController,WrappedArray((style,SeqPipe), (level,InnerControl)))
    val x2137 = withCtrl(x2214) { withName(withCtx(UnitController(style=SeqPipe,level=InnerControl), "TPCHQ6.scala:49:18"), "x2137") } // UnitPipe(List(b1270),Block(Const(())))
    // DefRhs(LhsSym(x2136,None),OpDef,WrappedArray((op,FixAdd), (inputs,List(b1269, Const(32)))))
    val x2136 = withCtrl(x2137) { withName(withCtx(OpDef(op=FixAdd,inputs=List(lookup[CounterIter]("b1269"), Const(32))), "TPCHQ6.scala:50:37"), "x2136") } // FixAdd(b1269,Const(32))
    // DefRhs(LhsSym(x2156,None),UnitController,WrappedArray((style,StreamPipe), (level,OuterControl)))
    val x2156 = withCtrl(x2214) { withName(withCtx(UnitController(style=StreamPipe,level=OuterControl), "TPCHQ6.scala:50:22"), "x2156") } // UnitPipe(List(b1270),Block(Const(())))
    // DefRhs(LhsMem(b2277,0,None),StreamOut,WrappedArray((field,"offset")))
    }; split5
    def split6 = {
    val b2277 = withCtrl(lookup[UnitController]("x2156")) { withAcc(withBD(withName(withCtx(StreamOut(field="offset"), "TPCHQ6.scala:50:22"), "b2277"), 1), false) } // StreamOutNew(BurstCmdBus)
    // DefRhs(LhsMem(b2278,0,None),StreamOut,WrappedArray((field,"size")))
    val b2278 = withCtrl(lookup[UnitController]("x2156")) { withAcc(withBD(withName(withCtx(StreamOut(field="size"), "TPCHQ6.scala:50:22"), "b2278"), 1), false) } // StreamOutNew(BurstCmdBus)
    // DefRhs(LhsMem(x2139,0,None),StreamIn,WrappedArray((field,"data")))
    val x2139 = withCtrl(lookup[UnitController]("x2156")) { withAcc(withBD(withName(withCtx(StreamIn(field="data"), "TPCHQ6.scala:50:22"), "x2139"), 1), false) } // StreamInNew(BurstDataBus())
    // DefRhs(LhsSym(x2147,None),UnitController,WrappedArray((style,SeqPipe), (level,InnerControl)))
    val x2147 = withCtrl(lookup[UnitController]("x2156")) { withName(withCtx(UnitController(style=SeqPipe,level=InnerControl), "TPCHQ6.scala:50:22"), "x2147") } // UnitPipe(List(b1270),Block(x2146))
    // AliasRhs(LhsSym(x2140,None),LhsSym(b1269,None))
    val x2140 = withName(lookup[CounterIter]("b1269"), "x2140") // FixConvert(b1269,TRUE,_32,_0) (Same Type. No op)
    // DefRhs(LhsSym(x2141,None),OpDef,WrappedArray((op,FixSla), (inputs,List(x2140, Const(2)))))
    val x2141 = withCtrl(x2147) { withName(withCtx(OpDef(op=FixSla,inputs=List(x2140, Const(2))), "TPCHQ6.scala:50:22"), "x2141") } // FixLsh(x2140,Const(2))
    }; split6
    def split7 = {
    // AliasRhs(LhsSym(x2142,None),LhsSym(x2141,None))
    val x2142 = withName(lookup[OpDef]("x2141"), "x2142") // FixConvert(x2141,TRUE,_64,_0)
    // DefRhs(LhsMem(x2143,0,None),DramAddress,WrappedArray((dram,x2116)))
    val x2143 = withCtrl(lookup[UnitController]("x2147")) { withName(withCtx(DramAddress(dram=lookup[DRAM]("x2116")), "TPCHQ6.scala:50:22"), "x2143") } // GetDRAMAddress(x2116)
    // DefRhs(LhsSym(x2144,None),OpDef,WrappedArray((op,FixAdd), (inputs,List(x2142, x2143))))
    val x2144 = withCtrl(lookup[UnitController]("x2147")) { withName(withCtx(OpDef(op=FixAdd,inputs=List(x2142, x2143)), "TPCHQ6.scala:50:22"), "x2144") } // SimpleStruct(ArrayBuffer((offset,x2144), (size,Const(128)), (isLoad,Const(true))))
    // x2145 = SimpleStruct(ArrayBuffer((offset,x2144), (size,Const(128)), (isLoad,Const(true))))
    // DefRhs(LhsSym(b2279,Some(b2277)),WriteMem,WrappedArray(LhsMem(b2277,0,None), x2144))
    val b2279_b2277 = withCtrl(lookup[UnitController]("x2147")) { withName(withCtx(WriteMem(lookup[StreamOut]("b2277"),x2144), "TPCHQ6.scala:50:22"), "b2279_b2277") } // StreamWrite(x2138,x2145,b1270)
    // DefRhs(LhsSym(b2280,Some(b2278)),WriteMem,WrappedArray(LhsMem(b2278,0,None), Const(128)))
    val b2280_b2278 = withCtrl(lookup[UnitController]("x2147")) { withName(withCtx(WriteMem(lookup[StreamOut]("b2278"),Const(128)), "TPCHQ6.scala:50:22"), "b2280_b2278") } // StreamWrite(x2138,x2145,b1270)
    }; split7
    def split8 = {
    // DefRhs(LhsSym(x2148,None),FringeDenseLoad,WrappedArray((dram,List(x2116)), (cmdStream,List(b2277, b2278)), (dataStream,List(x2139))))
    val x2148 = withCtrl(lookup[UnitController]("x2156")) { withName(withCtx(FringeDenseLoad(dram=List(lookup[DRAM]("x2116")),cmdStream=List(lookup[StreamOut]("b2277"), lookup[StreamOut]("b2278")),dataStream=List(lookup[StreamIn]("x2139"))), "TPCHQ6.scala:50:22"), "x2148") } // FringeDenseLoad(x2116,x2138,x2139)
    // DefRhs(LhsSym(x2149,None),Counter,WrappedArray((min,Const(0)), (max,Const(32)), (step,Const(1)), (par,16)))
    val x2149 = withCtrl(lookup[UnitController]("x2156")) { withName(withCtx(Counter(min=Const(0),max=Const(32),step=Const(1),par=16), "TPCHQ6.scala:50:22"), "x2149") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    // DefRhs(LhsSym(x2150,None),CounterChain,WrappedArray(List(x2149)))
    val x2150 = withCtrl(lookup[UnitController]("x2156")) { withName(withCtx(CounterChain(List(x2149)), "TPCHQ6.scala:50:22"), "x2150") } // CounterChainNew(List(x2149))
    // DefRhs(LhsSym(x2155,None),LoopController,WrappedArray((style,InnerPipe), (level,InnerControl), (cchain,x2150)))
    val x2155 = withCtrl(lookup[UnitController]("x2156")) { withName(withCtx(LoopController(style=InnerPipe,level=InnerControl,cchain=x2150), "TPCHQ6.scala:50:22"), "x2155") } // UnrolledForeach(List(b1270),x2150,Block(Const(())),List(List(b1290)),List(List(b1291)))
    // DefRhs(LhsSym(b1290,None),CounterIter,WrappedArray(x2149, None))
    val b1290 = withCtrl(x2155) { withName(CounterIter(x2149,None), "b1290") } // 
    // DefRhs(LhsSym(b1291,None),Const[Boolean],WrappedArray(true))
    }; split8
    def split9 = {
    val b1291 = withCtrl(lookup[LoopController]("x2155")) { withName(Const[Boolean](true), "b1291") } // 
    // DefRhs(LhsSym(x2151,None),OpDef,WrappedArray((op,BitAnd), (inputs,List(b1291, b1270))))
    val x2151 = withCtrl(lookup[LoopController]("x2155")) { withName(withCtx(OpDef(op=BitAnd,inputs=List(lookup[Const[Boolean]]("b1291"), lookup[Const[Boolean]]("b1270"))), "UnrollingBase.scala:28:66"), "x2151") } // And(b1291,b1270)
    // DefRhs(LhsSym(x2152,None),ReadMem,WrappedArray(LhsMem(x2139,0,None)))
    val x2152 = withCtrl(lookup[LoopController]("x2155")) { withName(withCtx(ReadMem(lookup[StreamIn]("x2139")), "TPCHQ6.scala:50:22"), "x2152") } // ParStreamRead(x2139,List(x2151))
    // AliasRhs(LhsSym(x2153,None),LhsSym(x2152,None))
    val x2153 = withName(x2152, "x2153") // VectorApply(x2152,0)
    // DefRhs(LhsSym(x2154,None),StoreBanks,WrappedArray(List(List(LhsMem(x2132,0,Some(0)))), List(b1290), x2153))
    val x2154 = withCtrl(lookup[LoopController]("x2155")) { withName(withCtx(StoreBanks(List(List(lookup[SRAM]("x2132_d0_b0"))),List(lookup[CounterIter]("b1290")),x2153), "TPCHQ6.scala:50:22"), "x2154") } // ParSRAMStore(x2132,List(List(b1290)),List(x2153),List(x2151))
    // DefRhs(LhsSym(x2175,None),UnitController,WrappedArray((style,StreamPipe), (level,OuterControl)))
    val x2175 = withCtrl(lookup[UnitController]("x2214")) { withName(withCtx(UnitController(style=StreamPipe,level=OuterControl), "TPCHQ6.scala:51:22"), "x2175") } // UnitPipe(List(b1270),Block(Const(())))
    }; split9
    def split10 = {
    // DefRhs(LhsMem(b2281,0,None),StreamOut,WrappedArray((field,"offset")))
    val b2281 = withCtrl(lookup[UnitController]("x2175")) { withAcc(withBD(withName(withCtx(StreamOut(field="offset"), "TPCHQ6.scala:51:22"), "b2281"), 1), false) } // StreamOutNew(BurstCmdBus)
    // DefRhs(LhsMem(b2282,0,None),StreamOut,WrappedArray((field,"size")))
    val b2282 = withCtrl(lookup[UnitController]("x2175")) { withAcc(withBD(withName(withCtx(StreamOut(field="size"), "TPCHQ6.scala:51:22"), "b2282"), 1), false) } // StreamOutNew(BurstCmdBus)
    // DefRhs(LhsMem(x2158,0,None),StreamIn,WrappedArray((field,"data")))
    val x2158 = withCtrl(lookup[UnitController]("x2175")) { withAcc(withBD(withName(withCtx(StreamIn(field="data"), "TPCHQ6.scala:51:22"), "x2158"), 1), false) } // StreamInNew(BurstDataBus())
    // DefRhs(LhsSym(x2166,None),UnitController,WrappedArray((style,SeqPipe), (level,InnerControl)))
    val x2166 = withCtrl(lookup[UnitController]("x2175")) { withName(withCtx(UnitController(style=SeqPipe,level=InnerControl), "TPCHQ6.scala:51:22"), "x2166") } // UnitPipe(List(b1270),Block(x2165))
    // AliasRhs(LhsSym(x2159,None),LhsSym(b1269,None))
    val x2159 = withName(lookup[CounterIter]("b1269"), "x2159") // FixConvert(b1269,TRUE,_32,_0) (Same Type. No op)
    // DefRhs(LhsSym(x2160,None),OpDef,WrappedArray((op,FixSla), (inputs,List(x2159, Const(2)))))
    }; split10
    def split11 = {
    val x2160 = withCtrl(lookup[UnitController]("x2166")) { withName(withCtx(OpDef(op=FixSla,inputs=List(lookup[CounterIter]("x2159"), Const(2))), "TPCHQ6.scala:51:22"), "x2160") } // FixLsh(x2159,Const(2))
    // AliasRhs(LhsSym(x2161,None),LhsSym(x2160,None))
    val x2161 = withName(lookup[OpDef]("x2160"), "x2161") // FixConvert(x2160,TRUE,_64,_0)
    // DefRhs(LhsMem(x2162,0,None),DramAddress,WrappedArray((dram,x2118)))
    val x2162 = withCtrl(lookup[UnitController]("x2166")) { withName(withCtx(DramAddress(dram=lookup[DRAM]("x2118")), "TPCHQ6.scala:51:22"), "x2162") } // GetDRAMAddress(x2118)
    // DefRhs(LhsSym(x2163,None),OpDef,WrappedArray((op,FixAdd), (inputs,List(x2161, x2162))))
    val x2163 = withCtrl(lookup[UnitController]("x2166")) { withName(withCtx(OpDef(op=FixAdd,inputs=List(x2161, x2162)), "TPCHQ6.scala:51:22"), "x2163") } // SimpleStruct(ArrayBuffer((offset,x2163), (size,Const(128)), (isLoad,Const(true))))
    // x2164 = SimpleStruct(ArrayBuffer((offset,x2163), (size,Const(128)), (isLoad,Const(true))))
    // DefRhs(LhsSym(b2283,Some(b2281)),WriteMem,WrappedArray(LhsMem(b2281,0,None), x2163))
    val b2283_b2281 = withCtrl(lookup[UnitController]("x2166")) { withName(withCtx(WriteMem(lookup[StreamOut]("b2281"),x2163), "TPCHQ6.scala:51:22"), "b2283_b2281") } // StreamWrite(x2157,x2164,b1270)
    // DefRhs(LhsSym(b2284,Some(b2282)),WriteMem,WrappedArray(LhsMem(b2282,0,None), Const(128)))
    }; split11
    def split12 = {
    val b2284_b2282 = withCtrl(lookup[UnitController]("x2166")) { withName(withCtx(WriteMem(lookup[StreamOut]("b2282"),Const(128)), "TPCHQ6.scala:51:22"), "b2284_b2282") } // StreamWrite(x2157,x2164,b1270)
    // DefRhs(LhsSym(x2167,None),FringeDenseLoad,WrappedArray((dram,List(x2118)), (cmdStream,List(b2281, b2282)), (dataStream,List(x2158))))
    val x2167 = withCtrl(lookup[UnitController]("x2175")) { withName(withCtx(FringeDenseLoad(dram=List(lookup[DRAM]("x2118")),cmdStream=List(lookup[StreamOut]("b2281"), lookup[StreamOut]("b2282")),dataStream=List(lookup[StreamIn]("x2158"))), "TPCHQ6.scala:51:22"), "x2167") } // FringeDenseLoad(x2118,x2157,x2158)
    // DefRhs(LhsSym(x2168,None),Counter,WrappedArray((min,Const(0)), (max,Const(32)), (step,Const(1)), (par,16)))
    val x2168 = withCtrl(lookup[UnitController]("x2175")) { withName(withCtx(Counter(min=Const(0),max=Const(32),step=Const(1),par=16), "TPCHQ6.scala:51:22"), "x2168") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    // DefRhs(LhsSym(x2169,None),CounterChain,WrappedArray(List(x2168)))
    val x2169 = withCtrl(lookup[UnitController]("x2175")) { withName(withCtx(CounterChain(List(x2168)), "TPCHQ6.scala:51:22"), "x2169") } // CounterChainNew(List(x2168))
    // DefRhs(LhsSym(x2174,None),LoopController,WrappedArray((style,InnerPipe), (level,InnerControl), (cchain,x2169)))
    val x2174 = withCtrl(lookup[UnitController]("x2175")) { withName(withCtx(LoopController(style=InnerPipe,level=InnerControl,cchain=x2169), "TPCHQ6.scala:51:22"), "x2174") } // UnrolledForeach(List(b1270),x2169,Block(Const(())),List(List(b1311)),List(List(b1312)))
    // DefRhs(LhsSym(b1311,None),CounterIter,WrappedArray(x2168, None))
    val b1311 = withCtrl(x2174) { withName(CounterIter(x2168,None), "b1311") } // 
    }; split12
    def split13 = {
    // DefRhs(LhsSym(b1312,None),Const[Boolean],WrappedArray(true))
    val b1312 = withCtrl(lookup[LoopController]("x2174")) { withName(Const[Boolean](true), "b1312") } // 
    // DefRhs(LhsSym(x2170,None),OpDef,WrappedArray((op,BitAnd), (inputs,List(b1312, b1270))))
    val x2170 = withCtrl(lookup[LoopController]("x2174")) { withName(withCtx(OpDef(op=BitAnd,inputs=List(b1312, lookup[Const[Boolean]]("b1270"))), "UnrollingBase.scala:28:66"), "x2170") } // And(b1312,b1270)
    // DefRhs(LhsSym(x2171,None),ReadMem,WrappedArray(LhsMem(x2158,0,None)))
    val x2171 = withCtrl(lookup[LoopController]("x2174")) { withName(withCtx(ReadMem(lookup[StreamIn]("x2158")), "TPCHQ6.scala:51:22"), "x2171") } // ParStreamRead(x2158,List(x2170))
    // AliasRhs(LhsSym(x2172,None),LhsSym(x2171,None))
    val x2172 = withName(x2171, "x2172") // VectorApply(x2171,0)
    // DefRhs(LhsSym(x2173,None),StoreBanks,WrappedArray(List(List(LhsMem(x2133,0,Some(0)))), List(b1311), x2172))
    val x2173 = withCtrl(lookup[LoopController]("x2174")) { withName(withCtx(StoreBanks(List(List(lookup[SRAM]("x2133_d0_b0"))),List(lookup[CounterIter]("b1311")),x2172), "TPCHQ6.scala:51:22"), "x2173") } // ParSRAMStore(x2133,List(List(b1311)),List(x2172),List(x2170))
    // DefRhs(LhsSym(x2194,None),UnitController,WrappedArray((style,StreamPipe), (level,OuterControl)))
    }; split13
    def split14 = {
    val x2194 = withCtrl(lookup[UnitController]("x2214")) { withName(withCtx(UnitController(style=StreamPipe,level=OuterControl), "TPCHQ6.scala:52:22"), "x2194") } // UnitPipe(List(b1270),Block(Const(())))
    // DefRhs(LhsMem(b2285,0,None),StreamOut,WrappedArray((field,"offset")))
    val b2285 = withCtrl(lookup[UnitController]("x2194")) { withAcc(withBD(withName(withCtx(StreamOut(field="offset"), "TPCHQ6.scala:52:22"), "b2285"), 1), false) } // StreamOutNew(BurstCmdBus)
    // DefRhs(LhsMem(b2286,0,None),StreamOut,WrappedArray((field,"size")))
    val b2286 = withCtrl(lookup[UnitController]("x2194")) { withAcc(withBD(withName(withCtx(StreamOut(field="size"), "TPCHQ6.scala:52:22"), "b2286"), 1), false) } // StreamOutNew(BurstCmdBus)
    // DefRhs(LhsMem(x2177,0,None),StreamIn,WrappedArray((field,"data")))
    val x2177 = withCtrl(lookup[UnitController]("x2194")) { withAcc(withBD(withName(withCtx(StreamIn(field="data"), "TPCHQ6.scala:52:22"), "x2177"), 1), false) } // StreamInNew(BurstDataBus())
    // DefRhs(LhsSym(x2185,None),UnitController,WrappedArray((style,SeqPipe), (level,InnerControl)))
    val x2185 = withCtrl(lookup[UnitController]("x2194")) { withName(withCtx(UnitController(style=SeqPipe,level=InnerControl), "TPCHQ6.scala:52:22"), "x2185") } // UnitPipe(List(b1270),Block(x2184))
    // AliasRhs(LhsSym(x2178,None),LhsSym(b1269,None))
    val x2178 = withName(lookup[CounterIter]("b1269"), "x2178") // FixConvert(b1269,TRUE,_32,_0) (Same Type. No op)
    }; split14
    def split15 = {
    // DefRhs(LhsSym(x2179,None),OpDef,WrappedArray((op,FixSla), (inputs,List(x2178, Const(2)))))
    val x2179 = withCtrl(lookup[UnitController]("x2185")) { withName(withCtx(OpDef(op=FixSla,inputs=List(lookup[CounterIter]("x2178"), Const(2))), "TPCHQ6.scala:52:22"), "x2179") } // FixLsh(x2178,Const(2))
    // AliasRhs(LhsSym(x2180,None),LhsSym(x2179,None))
    val x2180 = withName(x2179, "x2180") // FixConvert(x2179,TRUE,_64,_0)
    // DefRhs(LhsMem(x2181,0,None),DramAddress,WrappedArray((dram,x2120)))
    val x2181 = withCtrl(lookup[UnitController]("x2185")) { withName(withCtx(DramAddress(dram=lookup[DRAM]("x2120")), "TPCHQ6.scala:52:22"), "x2181") } // GetDRAMAddress(x2120)
    // DefRhs(LhsSym(x2182,None),OpDef,WrappedArray((op,FixAdd), (inputs,List(x2180, x2181))))
    val x2182 = withCtrl(lookup[UnitController]("x2185")) { withName(withCtx(OpDef(op=FixAdd,inputs=List(x2180, x2181)), "TPCHQ6.scala:52:22"), "x2182") } // SimpleStruct(ArrayBuffer((offset,x2182), (size,Const(128)), (isLoad,Const(true))))
    // x2183 = SimpleStruct(ArrayBuffer((offset,x2182), (size,Const(128)), (isLoad,Const(true))))
    // DefRhs(LhsSym(b2287,Some(b2285)),WriteMem,WrappedArray(LhsMem(b2285,0,None), x2182))
    val b2287_b2285 = withCtrl(lookup[UnitController]("x2185")) { withName(withCtx(WriteMem(lookup[StreamOut]("b2285"),x2182), "TPCHQ6.scala:52:22"), "b2287_b2285") } // StreamWrite(x2176,x2183,b1270)
    }; split15
    def split16 = {
    // DefRhs(LhsSym(b2288,Some(b2286)),WriteMem,WrappedArray(LhsMem(b2286,0,None), Const(128)))
    val b2288_b2286 = withCtrl(lookup[UnitController]("x2185")) { withName(withCtx(WriteMem(lookup[StreamOut]("b2286"),Const(128)), "TPCHQ6.scala:52:22"), "b2288_b2286") } // StreamWrite(x2176,x2183,b1270)
    // DefRhs(LhsSym(x2186,None),FringeDenseLoad,WrappedArray((dram,List(x2120)), (cmdStream,List(b2285, b2286)), (dataStream,List(x2177))))
    val x2186 = withCtrl(lookup[UnitController]("x2194")) { withName(withCtx(FringeDenseLoad(dram=List(lookup[DRAM]("x2120")),cmdStream=List(lookup[StreamOut]("b2285"), lookup[StreamOut]("b2286")),dataStream=List(lookup[StreamIn]("x2177"))), "TPCHQ6.scala:52:22"), "x2186") } // FringeDenseLoad(x2120,x2176,x2177)
    // DefRhs(LhsSym(x2187,None),Counter,WrappedArray((min,Const(0)), (max,Const(32)), (step,Const(1)), (par,16)))
    val x2187 = withCtrl(lookup[UnitController]("x2194")) { withName(withCtx(Counter(min=Const(0),max=Const(32),step=Const(1),par=16), "TPCHQ6.scala:52:22"), "x2187") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    // DefRhs(LhsSym(x2188,None),CounterChain,WrappedArray(List(x2187)))
    val x2188 = withCtrl(lookup[UnitController]("x2194")) { withName(withCtx(CounterChain(List(x2187)), "TPCHQ6.scala:52:22"), "x2188") } // CounterChainNew(List(x2187))
    // DefRhs(LhsSym(x2193,None),LoopController,WrappedArray((style,InnerPipe), (level,InnerControl), (cchain,x2188)))
    val x2193 = withCtrl(lookup[UnitController]("x2194")) { withName(withCtx(LoopController(style=InnerPipe,level=InnerControl,cchain=x2188), "TPCHQ6.scala:52:22"), "x2193") } // UnrolledForeach(List(b1270),x2188,Block(Const(())),List(List(b1332)),List(List(b1333)))
    // DefRhs(LhsSym(b1332,None),CounterIter,WrappedArray(x2187, None))
    }; split16
    def split17 = {
    val b1332 = withCtrl(lookup[LoopController]("x2193")) { withName(CounterIter(lookup[Counter]("x2187"),None), "b1332") } // 
    // DefRhs(LhsSym(b1333,None),Const[Boolean],WrappedArray(true))
    val b1333 = withCtrl(lookup[LoopController]("x2193")) { withName(Const[Boolean](true), "b1333") } // 
    // DefRhs(LhsSym(x2189,None),OpDef,WrappedArray((op,BitAnd), (inputs,List(b1333, b1270))))
    val x2189 = withCtrl(lookup[LoopController]("x2193")) { withName(withCtx(OpDef(op=BitAnd,inputs=List(b1333, lookup[Const[Boolean]]("b1270"))), "UnrollingBase.scala:28:66"), "x2189") } // And(b1333,b1270)
    // DefRhs(LhsSym(x2190,None),ReadMem,WrappedArray(LhsMem(x2177,0,None)))
    val x2190 = withCtrl(lookup[LoopController]("x2193")) { withName(withCtx(ReadMem(lookup[StreamIn]("x2177")), "TPCHQ6.scala:52:22"), "x2190") } // ParStreamRead(x2177,List(x2189))
    // AliasRhs(LhsSym(x2191,None),LhsSym(x2190,None))
    val x2191 = withName(x2190, "x2191") // VectorApply(x2190,0)
    // DefRhs(LhsSym(x2192,None),StoreBanks,WrappedArray(List(List(LhsMem(x2134,0,Some(0)))), List(b1332), x2191))
    val x2192 = withCtrl(lookup[LoopController]("x2193")) { withName(withCtx(StoreBanks(List(List(lookup[SRAM]("x2134_d0_b0"))),List(lookup[CounterIter]("b1332")),x2191), "TPCHQ6.scala:52:22"), "x2192") } // ParSRAMStore(x2134,List(List(b1332)),List(x2191),List(x2189))
    }; split17
    def split18 = {
    // DefRhs(LhsSym(x2213,None),UnitController,WrappedArray((style,StreamPipe), (level,OuterControl)))
    val x2213 = withCtrl(lookup[UnitController]("x2214")) { withName(withCtx(UnitController(style=StreamPipe,level=OuterControl), "TPCHQ6.scala:53:22"), "x2213") } // UnitPipe(List(b1270),Block(Const(())))
    // DefRhs(LhsMem(b2289,0,None),StreamOut,WrappedArray((field,"offset")))
    val b2289 = withCtrl(x2213) { withAcc(withBD(withName(withCtx(StreamOut(field="offset"), "TPCHQ6.scala:53:22"), "b2289"), 1), false) } // StreamOutNew(BurstCmdBus)
    // DefRhs(LhsMem(b2290,0,None),StreamOut,WrappedArray((field,"size")))
    val b2290 = withCtrl(x2213) { withAcc(withBD(withName(withCtx(StreamOut(field="size"), "TPCHQ6.scala:53:22"), "b2290"), 1), false) } // StreamOutNew(BurstCmdBus)
    // DefRhs(LhsMem(x2196,0,None),StreamIn,WrappedArray((field,"data")))
    val x2196 = withCtrl(x2213) { withAcc(withBD(withName(withCtx(StreamIn(field="data"), "TPCHQ6.scala:53:22"), "x2196"), 1), false) } // StreamInNew(BurstDataBus())
    // DefRhs(LhsSym(x2204,None),UnitController,WrappedArray((style,SeqPipe), (level,InnerControl)))
    val x2204 = withCtrl(x2213) { withName(withCtx(UnitController(style=SeqPipe,level=InnerControl), "TPCHQ6.scala:53:22"), "x2204") } // UnitPipe(List(b1270),Block(x2203))
    // AliasRhs(LhsSym(x2197,None),LhsSym(b1269,None))
    }; split18
    def split19 = {
    val x2197 = withName(lookup[CounterIter]("b1269"), "x2197") // FixConvert(b1269,TRUE,_32,_0) (Same Type. No op)
    // DefRhs(LhsSym(x2198,None),OpDef,WrappedArray((op,FixSla), (inputs,List(x2197, Const(2)))))
    val x2198 = withCtrl(lookup[UnitController]("x2204")) { withName(withCtx(OpDef(op=FixSla,inputs=List(lookup[CounterIter]("x2197"), Const(2))), "TPCHQ6.scala:53:22"), "x2198") } // FixLsh(x2197,Const(2))
    // AliasRhs(LhsSym(x2199,None),LhsSym(x2198,None))
    val x2199 = withName(x2198, "x2199") // FixConvert(x2198,TRUE,_64,_0)
    // DefRhs(LhsMem(x2200,0,None),DramAddress,WrappedArray((dram,x2122)))
    val x2200 = withCtrl(lookup[UnitController]("x2204")) { withName(withCtx(DramAddress(dram=lookup[DRAM]("x2122")), "TPCHQ6.scala:53:22"), "x2200") } // GetDRAMAddress(x2122)
    // DefRhs(LhsSym(x2201,None),OpDef,WrappedArray((op,FixAdd), (inputs,List(x2199, x2200))))
    val x2201 = withCtrl(lookup[UnitController]("x2204")) { withName(withCtx(OpDef(op=FixAdd,inputs=List(x2199, x2200)), "TPCHQ6.scala:53:22"), "x2201") } // SimpleStruct(ArrayBuffer((offset,x2201), (size,Const(128)), (isLoad,Const(true))))
    // x2202 = SimpleStruct(ArrayBuffer((offset,x2201), (size,Const(128)), (isLoad,Const(true))))
    // DefRhs(LhsSym(b2291,Some(b2289)),WriteMem,WrappedArray(LhsMem(b2289,0,None), x2201))
    }; split19
    def split20 = {
    val b2291_b2289 = withCtrl(lookup[UnitController]("x2204")) { withName(withCtx(WriteMem(lookup[StreamOut]("b2289"),lookup[OpDef]("x2201")), "TPCHQ6.scala:53:22"), "b2291_b2289") } // StreamWrite(x2195,x2202,b1270)
    // DefRhs(LhsSym(b2292,Some(b2290)),WriteMem,WrappedArray(LhsMem(b2290,0,None), Const(128)))
    val b2292_b2290 = withCtrl(lookup[UnitController]("x2204")) { withName(withCtx(WriteMem(lookup[StreamOut]("b2290"),Const(128)), "TPCHQ6.scala:53:22"), "b2292_b2290") } // StreamWrite(x2195,x2202,b1270)
    // DefRhs(LhsSym(x2205,None),FringeDenseLoad,WrappedArray((dram,List(x2122)), (cmdStream,List(b2289, b2290)), (dataStream,List(x2196))))
    val x2205 = withCtrl(lookup[UnitController]("x2213")) { withName(withCtx(FringeDenseLoad(dram=List(lookup[DRAM]("x2122")),cmdStream=List(lookup[StreamOut]("b2289"), lookup[StreamOut]("b2290")),dataStream=List(lookup[StreamIn]("x2196"))), "TPCHQ6.scala:53:22"), "x2205") } // FringeDenseLoad(x2122,x2195,x2196)
    // DefRhs(LhsSym(x2206,None),Counter,WrappedArray((min,Const(0)), (max,Const(32)), (step,Const(1)), (par,16)))
    val x2206 = withCtrl(lookup[UnitController]("x2213")) { withName(withCtx(Counter(min=Const(0),max=Const(32),step=Const(1),par=16), "TPCHQ6.scala:53:22"), "x2206") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    // DefRhs(LhsSym(x2207,None),CounterChain,WrappedArray(List(x2206)))
    val x2207 = withCtrl(lookup[UnitController]("x2213")) { withName(withCtx(CounterChain(List(x2206)), "TPCHQ6.scala:53:22"), "x2207") } // CounterChainNew(List(x2206))
    // DefRhs(LhsSym(x2212,None),LoopController,WrappedArray((style,InnerPipe), (level,InnerControl), (cchain,x2207)))
    val x2212 = withCtrl(lookup[UnitController]("x2213")) { withName(withCtx(LoopController(style=InnerPipe,level=InnerControl,cchain=x2207), "TPCHQ6.scala:53:22"), "x2212") } // UnrolledForeach(List(b1270),x2207,Block(Const(())),List(List(b1353)),List(List(b1354)))
    }; split20
    def split21 = {
    // DefRhs(LhsSym(b1353,None),CounterIter,WrappedArray(x2206, None))
    val b1353 = withCtrl(lookup[LoopController]("x2212")) { withName(CounterIter(lookup[Counter]("x2206"),None), "b1353") } // 
    // DefRhs(LhsSym(b1354,None),Const[Boolean],WrappedArray(true))
    val b1354 = withCtrl(lookup[LoopController]("x2212")) { withName(Const[Boolean](true), "b1354") } // 
    // DefRhs(LhsSym(x2208,None),OpDef,WrappedArray((op,BitAnd), (inputs,List(b1354, b1270))))
    val x2208 = withCtrl(lookup[LoopController]("x2212")) { withName(withCtx(OpDef(op=BitAnd,inputs=List(b1354, lookup[Const[Boolean]]("b1270"))), "UnrollingBase.scala:28:66"), "x2208") } // And(b1354,b1270)
    // DefRhs(LhsSym(x2209,None),ReadMem,WrappedArray(LhsMem(x2196,0,None)))
    val x2209 = withCtrl(lookup[LoopController]("x2212")) { withName(withCtx(ReadMem(lookup[StreamIn]("x2196")), "TPCHQ6.scala:53:22"), "x2209") } // ParStreamRead(x2196,List(x2208))
    // AliasRhs(LhsSym(x2210,None),LhsSym(x2209,None))
    val x2210 = withName(x2209, "x2210") // VectorApply(x2209,0)
    // DefRhs(LhsSym(x2211,None),StoreBanks,WrappedArray(List(List(LhsMem(x2135,0,Some(0)))), List(b1353), x2210))
    }; split21
    def split22 = {
    val x2211 = withCtrl(lookup[LoopController]("x2212")) { withName(withCtx(StoreBanks(List(List(lookup[SRAM]("x2135_d0_b0"))),List(lookup[CounterIter]("b1353")),lookup[ReadMem]("x2210")), "TPCHQ6.scala:53:22"), "x2211") } // ParSRAMStore(x2135,List(List(b1353)),List(x2210),List(x2208))
    // DefRhs(LhsMem(x2215,0,None),Reg,WrappedArray((init,Some(0))))
    val x2215_d0 = withCtrl(lookup[LoopController]("x2249")) { withAcc(withBD(withName(withCtx(Reg(init=Some(0)), "TPCHQ6.scala:55:19"), "x2215_d0"), 2), false) } // RegNew(Const(0))
    // DefRhs(LhsMem(x2215,1,None),Reg,WrappedArray((init,Some(0))))
    val x2215_d1 = withCtrl(lookup[LoopController]("x2249")) { withAcc(withBD(withName(withCtx(Reg(init=Some(0)), "TPCHQ6.scala:55:19"), "x2215_d1"), 1), true) } // RegNew(Const(0))
    // DefRhs(LhsSym(x2216,None),Counter,WrappedArray((min,Const(0)), (max,Const(32)), (step,Const(1)), (par,16)))
    val x2216 = withCtrl(lookup[LoopController]("x2249")) { withName(withCtx(Counter(min=Const(0),max=Const(32),step=Const(1),par=16), "TPCHQ6.scala:55:27"), "x2216") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    // DefRhs(LhsSym(x2217,None),CounterChain,WrappedArray(List(x2216)))
    val x2217 = withCtrl(lookup[LoopController]("x2249")) { withName(withCtx(CounterChain(List(x2216)), "TPCHQ6.scala:62:10"), "x2217") } // CounterChainNew(List(x2216))
    // DefRhs(LhsSym(x2242,None),LoopController,WrappedArray((style,InnerPipe), (level,InnerControl), (cchain,x2217)))
    val x2242 = withCtrl(lookup[LoopController]("x2249")) { withName(withCtx(LoopController(style=InnerPipe,level=InnerControl,cchain=x2217), "TPCHQ6.scala:62:10"), "x2242") } // UnrolledReduce(List(b1270),x2217,x2215,Block((x2215) => Const(())),List(List(b1365)),List(List(b1366)))
    }; split22
    def split23 = {
    // DefRhs(LhsSym(b1365,None),CounterIter,WrappedArray(x2216, None))
    val b1365 = withCtrl(lookup[LoopController]("x2242")) { withName(CounterIter(lookup[Counter]("x2216"),None), "b1365") } // 
    // DefRhs(LhsSym(b1366,None),Const[Boolean],WrappedArray(true))
    val b1366 = withCtrl(lookup[LoopController]("x2242")) { withName(Const[Boolean](true), "b1366") } // 
    // DefRhs(LhsSym(x2218,None),OpDef,WrappedArray((op,BitAnd), (inputs,List(b1366, b1270))))
    val x2218 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(OpDef(op=BitAnd,inputs=List(b1366, lookup[Const[Boolean]]("b1270"))), "UnrollingBase.scala:28:66"), "x2218") } // And(b1366,b1270)
    // DefRhs(LhsSym(x2219,None),LoadBanks,WrappedArray(List(LhsMem(x2132,0,Some(0))), List(b1365)))
    val x2219 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(LoadBanks(List(lookup[SRAM]("x2132_d0_b0")),List(b1365)), "TPCHQ6.scala:56:32:date"), "x2219") } // ParSRAMLoad(x2132,List(List(b1365)),List(x2218))
    // AliasRhs(LhsSym(x2220,None),LhsSym(x2219,None))
    val x2220 = withName(x2219, "x2220") // VectorApply(x2219,0)
    // DefRhs(LhsSym(x2221,None),LoadBanks,WrappedArray(List(LhsMem(x2134,0,Some(0))), List(b1365)))
    }; split23
    def split24 = {
    val x2221 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(LoadBanks(List(lookup[SRAM]("x2134_d0_b0")),List(lookup[CounterIter]("b1365"))), "TPCHQ6.scala:57:33:disct"), "x2221") } // ParSRAMLoad(x2134,List(List(b1365)),List(x2218))
    // AliasRhs(LhsSym(x2222,None),LhsSym(x2221,None))
    val x2222 = withName(lookup[LoadBanks]("x2221"), "x2222") // VectorApply(x2221,0)
    // DefRhs(LhsSym(x2223,None),LoadBanks,WrappedArray(List(LhsMem(x2133,0,Some(0))), List(b1365)))
    val x2223 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(LoadBanks(List(lookup[SRAM]("x2133_d0_b0")),List(lookup[CounterIter]("b1365"))), "TPCHQ6.scala:58:33:quant"), "x2223") } // ParSRAMLoad(x2133,List(List(b1365)),List(x2218))
    // AliasRhs(LhsSym(x2224,None),LhsSym(x2223,None))
    val x2224 = withName(x2223, "x2224") // VectorApply(x2223,0)
    // DefRhs(LhsSym(x2225,None),LoadBanks,WrappedArray(List(LhsMem(x2135,0,Some(0))), List(b1365)))
    val x2225 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(LoadBanks(List(lookup[SRAM]("x2135_d0_b0")),List(lookup[CounterIter]("b1365"))), "TPCHQ6.scala:59:33:price"), "x2225") } // ParSRAMLoad(x2135,List(List(b1365)),List(x2218))
    // AliasRhs(LhsSym(x2226,None),LhsSym(x2225,None))
    val x2226 = withName(x2225, "x2226") // VectorApply(x2225,0)
    }; split24
    def split25 = {
    // DefRhs(LhsSym(x2227,None),OpDef,WrappedArray((op,FixLt), (inputs,List(Const(0), x2220))))
    val x2227 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(OpDef(op=FixLt,inputs=List(Const(0), lookup[LoadBanks]("x2220"))), "TPCHQ6.scala:60:28"), "x2227") } // FixLt(Const(0),x2220)
    // DefRhs(LhsSym(x2228,None),OpDef,WrappedArray((op,FixLt), (inputs,List(x2220, Const(9999)))))
    val x2228 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(OpDef(op=FixLt,inputs=List(lookup[LoadBanks]("x2220"), Const(9999))), "TPCHQ6.scala:60:46"), "x2228") } // FixLt(x2220,Const(9999))
    // DefRhs(LhsSym(x2229,None),OpDef,WrappedArray((op,BitAnd), (inputs,List(x2227, x2228))))
    val x2229 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(OpDef(op=BitAnd,inputs=List(x2227, x2228)), "TPCHQ6.scala:60:38"), "x2229") } // And(x2227,x2228)
    // DefRhs(LhsSym(x2230,None),OpDef,WrappedArray((op,FixLeq), (inputs,List(Const(0), x2222))))
    val x2230 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(OpDef(op=FixLeq,inputs=List(Const(0), lookup[LoadBanks]("x2222"))), "TPCHQ6.scala:60:65"), "x2230") } // FixLeq(Const(0),x2222)
    // DefRhs(LhsSym(x2231,None),OpDef,WrappedArray((op,BitAnd), (inputs,List(x2229, x2230))))
    val x2231 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(OpDef(op=BitAnd,inputs=List(x2229, x2230)), "TPCHQ6.scala:60:56"), "x2231") } // And(x2229,x2230)
    // DefRhs(LhsSym(x2232,None),OpDef,WrappedArray((op,FixLeq), (inputs,List(x2222, Const(9999)))))
    }; split25
    def split26 = {
    val x2232 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(OpDef(op=FixLeq,inputs=List(lookup[LoadBanks]("x2222"), Const(9999))), "TPCHQ6.scala:60:92"), "x2232") } // FixLeq(x2222,Const(9999))
    // DefRhs(LhsSym(x2233,None),OpDef,WrappedArray((op,BitAnd), (inputs,List(x2231, x2232))))
    val x2233 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(OpDef(op=BitAnd,inputs=List(lookup[OpDef]("x2231"), lookup[OpDef]("x2232"))), "TPCHQ6.scala:60:83"), "x2233") } // And(x2231,x2232)
    // DefRhs(LhsSym(x2234,None),OpDef,WrappedArray((op,FixLt), (inputs,List(x2224, Const(24)))))
    val x2234 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(OpDef(op=FixLt,inputs=List(lookup[LoadBanks]("x2224"), Const(24))), "TPCHQ6.scala:60:119"), "x2234") } // FixLt(x2224,Const(24))
    // DefRhs(LhsSym(x2235,None),OpDef,WrappedArray((op,BitAnd), (inputs,List(x2233, x2234))))
    val x2235 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(OpDef(op=BitAnd,inputs=List(x2233, x2234)), "TPCHQ6.scala:60:110:valid"), "x2235") } // And(x2233,x2234)
    // DefRhs(LhsSym(x2236,None),OpDef,WrappedArray((op,FixMul), (inputs,List(x2226, x2222))))
    val x2236 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(OpDef(op=FixMul,inputs=List(lookup[LoadBanks]("x2226"), lookup[LoadBanks]("x2222"))), "TPCHQ6.scala:61:28"), "x2236") } // FixMul(x2226,x2222)
    // DefRhs(LhsSym(x2237,None),OpDef,WrappedArray((op,MuxOp), (inputs,List(x2235, x2236, Const(0)))))
    val x2237 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(OpDef(op=MuxOp,inputs=List(x2235, x2236, Const(0))), "TPCHQ6.scala:61:14"), "x2237") } // Mux(x2235,x2236,Const(0))
    }; split26
    def split27 = {
    // DefRhs(LhsSym(x2238,None),ReadMem,WrappedArray(LhsMem(x2215,1,None)))
    val x2238 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(ReadMem(lookup[Reg]("x2215_d1")), "TPCHQ6.scala:62:10"), "x2238") } // RegRead(x2215)
    // DefRhs(LhsSym(x2239,None),OpDef,WrappedArray((op,FixEql), (inputs,List(b1365, Const(0)))))
    val x2239 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(OpDef(op=FixEql,inputs=List(lookup[CounterIter]("b1365"), Const(0))), "TPCHQ6.scala:62:10"), "x2239") } // FixEql(b1365,Const(0))
    // DefRhs(LhsSym(x2240,None),ReduceAccumOp,WrappedArray((op,FixAdd), (input,x2237), (accum,x2238)))
    val x2240 = withCtrl(lookup[LoopController]("x2242")) { withName(withCtx(ReduceAccumOp(op=FixAdd,input=lookup[OpDef]("x2237"),accum=x2238), "TPCHQ6.scala:62:12"), "x2240") } // FixAdd(x2237,x2238)
    // DefRhs(LhsSym(x2241,Some(x2215_d0)),WriteMem,WrappedArray(LhsMem(x2215,0,None), x2240))
    val x2241_x2215_d0 = withCtrl(lookup[LoopController]("x2242")) { withDeps(withName(withCtx(WriteMem(lookup[Reg]("x2215_d0"),x2240), "TPCHQ6.scala:62:10"), "x2241_x2215_d0"), List(x2238)) } // RegWrite(x2215,x2240,b1270)
    // DefRhs(LhsSym(x2241,Some(x2215_d1)),WriteMem,WrappedArray(LhsMem(x2215,1,None), x2240))
    val x2241_x2215_d1 = withCtrl(lookup[LoopController]("x2242")) { withDeps(withName(withCtx(WriteMem(lookup[Reg]("x2215_d1"),x2240), "TPCHQ6.scala:62:10"), "x2241_x2215_d1"), List(x2238)) } // RegWrite(x2215,x2240,b1270)
    // DefRhs(LhsSym(x2248,None),UnitController,WrappedArray((style,SeqPipe), (level,InnerControl)))
    }; split27
    def split28 = {
    val x2248 = withCtrl(lookup[LoopController]("x2249")) { withName(withCtx(UnitController(style=SeqPipe,level=InnerControl), "TPCHQ6.scala:63:8"), "x2248") } // UnitPipe(List(Const(true)),Block(x2247))
    // DefRhs(LhsSym(x2243,None),ReadMem,WrappedArray(LhsMem(x2128,1,None)))
    val x2243 = withCtrl(lookup[UnitController]("x2248")) { withName(withCtx(ReadMem(lookup[Reg]("x2128_d1")), "TPCHQ6.scala:63:8"), "x2243") } // RegRead(x2128)
    // DefRhs(LhsSym(x2244,None),OpDef,WrappedArray((op,FixEql), (inputs,List(b1269, Const(0)))))
    val x2244 = withCtrl(lookup[UnitController]("x2248")) { withName(withCtx(OpDef(op=FixEql,inputs=List(lookup[CounterIter]("b1269"), Const(0))), "TPCHQ6.scala:63:8"), "x2244") } // FixEql(b1269,Const(0))
    // DefRhs(LhsSym(x2245,None),ReadMem,WrappedArray(LhsMem(x2215,0,None)))
    val x2245 = withCtrl(lookup[UnitController]("x2248")) { withName(withCtx(ReadMem(lookup[Reg]("x2215_d0")), "TPCHQ6.scala:62:10"), "x2245") } // RegRead(x2215)
    // DefRhs(LhsSym(x2246,None),OpDef,WrappedArray((op,FixAdd), (inputs,List(x2245, x2243))))
    val x2246 = withCtrl(lookup[UnitController]("x2248")) { withName(withCtx(OpDef(op=FixAdd,inputs=List(x2245, x2243)), "TPCHQ6.scala:63:10"), "x2246") } // FixAdd(x2245,x2243)
    // DefRhs(LhsSym(x2247,Some(x2128_d0)),WriteMem,WrappedArray(LhsMem(x2128,0,None), x2246))
    val x2247_x2128_d0 = withCtrl(lookup[UnitController]("x2248")) { withDeps(withName(withCtx(WriteMem(lookup[Reg]("x2128_d0"),x2246), "TPCHQ6.scala:63:8"), "x2247_x2128_d0"), List(x2243)) } // RegWrite(x2128,x2246,Const(true))
    }; split28
    def split29 = {
    // DefRhs(LhsSym(x2247,Some(x2128_d1)),WriteMem,WrappedArray(LhsMem(x2128,1,None), x2246))
    val x2247_x2128_d1 = withCtrl(lookup[UnitController]("x2248")) { withDeps(withName(withCtx(WriteMem(lookup[Reg]("x2128_d1"),lookup[OpDef]("x2246")), "TPCHQ6.scala:63:8"), "x2247_x2128_d1"), List(lookup[ReadMem]("x2243"))) } // RegWrite(x2128,x2246,Const(true))
    // DefRhs(LhsSym(x2252,None),UnitController,WrappedArray((style,SeqPipe), (level,InnerControl)))
    val x2252 = withCtrl(lookup[UnitController]("x2253")) { withName(withCtx(UnitController(style=SeqPipe,level=InnerControl), "TPCHQ6.scala:39:11"), "x2252") } // UnitPipe(List(Const(true)),Block(Const(())))
    // DefRhs(LhsSym(x2250,None),ReadMem,WrappedArray(LhsMem(x2128,0,None)))
    val x2250 = withCtrl(x2252) { withName(withCtx(ReadMem(lookup[Reg]("x2128_d0")), "TPCHQ6.scala:65:14"), "x2250") } // RegRead(x2128)
    // DefRhs(LhsSym(x2251,Some(x2123)),WriteMem,WrappedArray(LhsMem(x2123,0,None), x2250))
    val x2251_x2123 = withCtrl(x2252) { withName(withCtx(WriteMem(lookup[ArgOut]("x2123"),x2250), "TPCHQ6.scala:65:11"), "x2251_x2123") } // RegWrite(x2123,x2250,Const(true))
    }; split29
    
  }
}
