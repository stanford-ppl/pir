//package prism
//package graph1

//import prism.graph._

//import scala.collection.mutable

//trait Edge[A<:Edge[A,B],B<:Edge[B,A]] extends IR { self:A =>

  ///*  ------- State -------- */
  //def src:Node[_]
  //val _connected:mutable.ListBuffer[B] = mutable.ListBuffer[B]()

  //def connected:List[B] = _connected.toList
  //def singleConnected:Option[B] = {
    //assert(connected.size <= 1, s"${this.src}.$this has more than 1 connection. connected to ${connected.map( c => s"${c.src}.$c")}")
    //connected.headOption
  //}
  //def isConnected:Boolean = connected.nonEmpty
  //def isConnectedTo(e:B):Boolean = connected.contains(e)
  //def isConnectedTo(n:Node[_]):Boolean = neighbors.contains(n)
  //def connect(e:B):this.type = {
    //if (e.src.Nct == src.Nct) {
      //_connected += e
      //e._connected += self
      //self
    //} else {
      //throw new Exception(s"Cannot connect edge with node type ${src.Nct} from $src to edge with node type ${e.src.Nct} from ${e.src}")
    //}
  //}
  //def neighbors = connected.map(_.src).distinct

  //def disconnectFrom(e:B):Unit = {
    //assert(self.isConnectedTo(e), s"${self.src}.$this is not connected to ${e.src}.$e. connected=$connected")
    //while (_connected.contains(e)) _connected -= e
    //while (e._connected.contains(self)) e._connected -= self
  //}
  //def disconnect = connected.distinct.foreach(disconnectFrom)

  ////src.addEdge(self)
//}

//class Input(implicit val src:Node[_]) extends Edge[Input, Output] {
  //def swapOutputConnection(from:Output, to:Output) = {
    //_connected.transform {
      //case `from` =>
        //to._connected += this
        //from._connected -= this
        //to
      //case from => from
    //}
  //}
//}

//class Output(implicit val src:Node[_]) extends Edge[Output, Input]

//trait Node[N<:Node[N]] extends IR { self:N =>

  //type TN = N

  ///*  ------- State -------- */
  //implicit def Nct:ClassTag[N]
  //private var _parent:Option[N] = None
  //private lazy val _children = ListBuffer[N]()
  //val localEdges = mutable.ListBuffer[Edge[_,_]]()

  ///*  ------- Metadata -------- */
  //val pos = Metadata[(Double,Double)]("pos")

  ///*  ------- functions -------- */
  //// Parent
  //def parent:Option[N] = _parent
  //def setParent(p:N):this.type =  {
    //assert(p != this, s"setting parent of $this to be the same as it self $p!")
    //_parent match {
      //case Some(`p`) => this
      //case Some(parent) => err(s"Resetting parent of $this from $parent to $p"); this
      //case None =>
        //p match {
          //case _:N =>
            //assert(!this.isAncestorOf(p), s"Setting descendent $p as parent of $this") 
            //_parent = Some(p)
            //p.addChild(this)
          //case _ => 
        //}
        //this
    //}
  //}
  //def unsetParent:self.type = {
    //parent.foreach { p =>
      //_parent = None
      //p.removeChild(this)
    //}
    //this
  //}
  //def resetParent(p:N):this.type = { unsetParent; setParent(p) }
  //def isParentOf(m:N) = m.parent == Some(this)

  //// Children
  //def children:List[N] = _children.toList
  //def addChild(cs:N*):this.type = { 
    //cs.foreach { c =>
      //assert(c != this, s"Cannot add self as a children node=$this")
      //c match {
        //case _:N => 
          //if (_children.contains(c)) return this
          //_children += c
          //c.setParent(this)
        //case c => 
          //throw new Exception(s"Cannot add $c as child of node $this with type $Nct")
      //}
    //}
    //this
  //}

  //def removeChild(c:N):Unit = {
    //if (!_children.contains(c)) return
    //_children -= c
    //c.unsetParent
  //}
  //def isChildOf(p:N) = p.children.contains(this)

  //def siblings:List[N] = parent.map { _.children.filterNot { _ == this} }.getOrElse(Nil)
  //def ancestors:List[N] = {
    //parent.toList.flatMap { parent => parent :: parent.ancestors }
  //}
  //def isAncestorOf(n:N):Boolean = n.isDescendentOf(this)
  //// Inclusive
  //def ancestorSlice(top:N) = { // from this to top inclusive
    //val chain = this :: this.ancestors
    //val idx = chain.indexOf(top)
    //assert(idx != -1, s"$top is not ancestor of the $this")
    //chain.slice(0, idx+1)
  //}
  //def descendents:List[N] = children.flatMap { child => child :: child.descendents }
  //def isDescendentOf(n:N):Boolean = parent.fold(false) { p => p == n || p.isDescendentOf(n) }

  //def addEdge(io:Edge[_,_]) = {
    //localEdges += io
  //}
  //def removeEdge(io:Edge[_,_]) = localEdges -= io
  //def localIns:ListBuffer[Input] = localEdges.collect { case i:Input => i }
  //def localOuts:ListBuffer[Output] = localEdges.collect { case i:Output => i }

  //def edges = localEdges ++ descendents.flatMap { _.localEdges }
  //def ins = edges.collect { case i:Input => i }
  //def outs = edges.collect { case i:Output => i }
  //def localDeps:Seq[N] = { 
    //localIns.flatMap { _.connected.map { _.src.as[N] } }.toSeq.distinct
  //}

  //def localDepeds:Seq[N] = {
    //localOuts.flatMap { _.connected.map { _.src.as[N] } }.toSeq.distinct
  //}

  //def matchLevel(n:N) = (n :: n.ancestors).filter { _.parent == this.parent }.headOption

  /*
   * A map of external dependent outputs and internal inputs that depends on the external 
   * dependencies
   * */
  //def depsFrom:Map[Output, Seq[Input]] = {
    //val descendents = this.descendents
    //val ins = localIns.toIterator ++ descendents.toIterator.flatMap { _.localIns }
    //ins.flatMap { in =>
      //in.connected.filterNot { out => descendents.contains(out.src) } 
      //.map { out => (out.as[Output], in) } 
    //}.toSeq.groupBy { _._1 }.mapValues { _.map { _._2 } }
  //}

  //def deps:Seq[N] = depsFrom.keys.map(_.src.as[N]).toSeq

  /*
   * A map of internal outs to seq of external inputs
   * */
  //def depedsTo:Map[Output, Seq[Input]] = {
    //val descendents = this.descendents
    //val outs = localOuts.toIterator ++ descendents.toIterator.flatMap { _.localOuts }
    //outs.flatMap { out =>
      //out.connected.filterNot { in => descendents.contains(in.src) } 
      //.map { in => (in.as[Input], out) } 
    //}.toSeq.groupBy { _._2 }.mapValues { _.map { _._1 } }
  //}

  //def depeds:Seq[N] = depedsTo.values.flatten.map { _.src.as[N] }.toSeq.distinct

  //def siblingDeps = deps.flatMap(matchLevel)
  //def globalDeps = deps.filter { d => matchLevel(d).isEmpty }
  //def siblingDepeds = depeds.flatMap(matchLevel)
  //def globalDepeds = depeds.filter { d => matchLevel(d).isEmpty }
  //def neighbors = deps ++ depeds

//}

//trait Edge extends IR { self =>

  //type A <: Edge { type A = self.A; type B = self.B }
  //type B <: Edge { type A = self.B; type B = self.A }

  ///*  ------- State -------- */
  //def src:Node[_]
  //val _connected:mutable.ListBuffer[B] = mutable.ListBuffer[B]()

  //def connected:List[B] = _connected.toList
  //def singleConnected:Option[B] = {
    //assert(connected.size <= 1, s"${this.src}.$this has more than 1 connection. connected to ${connected.map( c => s"${c.src}.$c")}")
    //connected.headOption
  //}
  //def isConnected:Boolean = connected.nonEmpty
  //def isConnectedTo(e:B):Boolean = connected.contains(e)
  //def isConnectedTo(n:Node[_]):Boolean = neighbors.contains(n)
  //def connect(e:B):this.type = {
    //if (e.src.Nct == src.Nct) {
      //_connected += e
      //e._connected += self
      //self
    //} else {
      //throw new Exception(s"Cannot connect edge with node type ${src.Nct} from $src to edge with node type ${e.src.Nct} from ${e.src}")
    //}
  //}
  //def neighbors = connected.map(_.src).distinct

  //def disconnectFrom(e:B):Unit = {
    //assert(self.isConnectedTo(e), s"${self.src}.$this is not connected to ${e.src}.$e. connected=$connected")
    //while (_connected.contains(e)) _connected -= e
    //while (e._connected.contains(self)) e._connected -= self
  //}
  //def disconnect = connected.distinct.foreach(disconnectFrom)

  ////src.addEdge(self)
//}

////class Input(implicit val src:Node[_]) extends Edge[Input, Output] {
  ////def swapOutputConnection(from:Output, to:Output) = {
    ////_connected.transform {
      ////case `from` =>
        ////to._connected += this
        ////from._connected -= this
        ////to
      ////case from => from
    ////}
  ////}
////}

////class Output(implicit val src:Node[_]) extends Edge[Output, Input]

////trait Node[N<:Node[N]] extends IR { self:N =>

  ////type TN = N

  /////*  ------- State -------- */
  ////implicit def Nct:ClassTag[N]
  ////private var _parent:Option[N] = None
  ////private lazy val _children = ListBuffer[N]()
  ////val localEdges = mutable.ListBuffer[Edge[_,_]]()

  /////*  ------- Metadata -------- */
  ////val pos = Metadata[(Double,Double)]("pos")

  /////*  ------- functions -------- */
  ////// Parent
  ////def parent:Option[N] = _parent
  ////def setParent(p:N):this.type =  {
    ////assert(p != this, s"setting parent of $this to be the same as it self $p!")
    ////_parent match {
      ////case Some(`p`) => this
      ////case Some(parent) => err(s"Resetting parent of $this from $parent to $p"); this
      ////case None =>
        ////p match {
          ////case _:N =>
            ////assert(!this.isAncestorOf(p), s"Setting descendent $p as parent of $this") 
            ////_parent = Some(p)
            ////p.addChild(this)
          ////case _ => 
        ////}
        ////this
    ////}
  ////}
  ////def unsetParent:self.type = {
    ////parent.foreach { p =>
      ////_parent = None
      ////p.removeChild(this)
    ////}
    ////this
  ////}
  ////def resetParent(p:N):this.type = { unsetParent; setParent(p) }
  ////def isParentOf(m:N) = m.parent == Some(this)

  ////// Children
  ////def children:List[N] = _children.toList
  ////def addChild(cs:N*):this.type = { 
    ////cs.foreach { c =>
      ////assert(c != this, s"Cannot add self as a children node=$this")
      ////c match {
        ////case _:N => 
          ////if (_children.contains(c)) return this
          ////_children += c
          ////c.setParent(this)
        ////case c => 
          ////throw new Exception(s"Cannot add $c as child of node $this with type $Nct")
      ////}
    ////}
    ////this
  ////}

  ////def removeChild(c:N):Unit = {
    ////if (!_children.contains(c)) return
    ////_children -= c
    ////c.unsetParent
  ////}
  ////def isChildOf(p:N) = p.children.contains(this)

  ////def siblings:List[N] = parent.map { _.children.filterNot { _ == this} }.getOrElse(Nil)
  ////def ancestors:List[N] = {
    ////parent.toList.flatMap { parent => parent :: parent.ancestors }
  ////}
  ////def isAncestorOf(n:N):Boolean = n.isDescendentOf(this)
  ////// Inclusive
  ////def ancestorSlice(top:N) = { // from this to top inclusive
    ////val chain = this :: this.ancestors
    ////val idx = chain.indexOf(top)
    ////assert(idx != -1, s"$top is not ancestor of the $this")
    ////chain.slice(0, idx+1)
  ////}
  ////def descendents:List[N] = children.flatMap { child => child :: child.descendents }
  ////def isDescendentOf(n:N):Boolean = parent.fold(false) { p => p == n || p.isDescendentOf(n) }

  ////def addEdge(io:Edge[_,_]) = {
    ////localEdges += io
  ////}
  ////def removeEdge(io:Edge[_,_]) = localEdges -= io
  ////def localIns:ListBuffer[Input] = localEdges.collect { case i:Input => i }
  ////def localOuts:ListBuffer[Output] = localEdges.collect { case i:Output => i }

  ////def edges = localEdges ++ descendents.flatMap { _.localEdges }
  ////def ins = edges.collect { case i:Input => i }
  ////def outs = edges.collect { case i:Output => i }
  ////def localDeps:Seq[N] = { 
    ////localIns.flatMap { _.connected.map { _.src.as[N] } }.toSeq.distinct
  ////}

  ////def localDepeds:Seq[N] = {
    ////localOuts.flatMap { _.connected.map { _.src.as[N] } }.toSeq.distinct
  ////}

  ////def matchLevel(n:N) = (n :: n.ancestors).filter { _.parent == this.parent }.headOption

  /*
   * A map of external dependent outputs and internal inputs that depends on the external 
   * dependencies
   * */
  ////def depsFrom:Map[Output, Seq[Input]] = {
    ////val descendents = this.descendents
    ////val ins = localIns.toIterator ++ descendents.toIterator.flatMap { _.localIns }
    ////ins.flatMap { in =>
      ////in.connected.filterNot { out => descendents.contains(out.src) } 
      ////.map { out => (out.as[Output], in) } 
    ////}.toSeq.groupBy { _._1 }.mapValues { _.map { _._2 } }
  ////}

  ////def deps:Seq[N] = depsFrom.keys.map(_.src.as[N]).toSeq

  /*
   * A map of internal outs to seq of external inputs
   * */
  ////def depedsTo:Map[Output, Seq[Input]] = {
    ////val descendents = this.descendents
    ////val outs = localOuts.toIterator ++ descendents.toIterator.flatMap { _.localOuts }
    ////outs.flatMap { out =>
      ////out.connected.filterNot { in => descendents.contains(in.src) } 
      ////.map { in => (in.as[Input], out) } 
    ////}.toSeq.groupBy { _._2 }.mapValues { _.map { _._1 } }
  ////}

  ////def depeds:Seq[N] = depedsTo.values.flatten.map { _.src.as[N] }.toSeq.distinct

  ////def siblingDeps = deps.flatMap(matchLevel)
  ////def globalDeps = deps.filter { d => matchLevel(d).isEmpty }
  ////def siblingDepeds = depeds.flatMap(matchLevel)
  ////def globalDepeds = depeds.filter { d => matchLevel(d).isEmpty }
  ////def neighbors = deps ++ depeds

////}
