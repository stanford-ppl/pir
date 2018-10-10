package spade
package codegen

import prism.traversal._

abstract class SpadeCodegen(implicit compiler:Spade) extends SpadeTraversal with ChildFirstTraversal with Codegen
