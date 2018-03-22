package prism

import prism.exceptions._

package object traversal {
  type EOption[T] = Either[MappingFailure, T]
}
