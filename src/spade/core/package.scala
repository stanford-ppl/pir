
import spade.node._

package object spade {
  /* ------------- Alias ------------- **/

  /* simulation */
  type Simulator = spade.simulation.Simulator
  type Simulatable = spade.simulation.Simulatable
  type Val[P<:PortType] = spade.simulation.Val[P]
  type Value = spade.simulation.Value
  type SingleValue = spade.simulation.SingleValue
  type BusValue = spade.simulation.BusValue

  /* network */
  type SwitchNetwork = spade.network.SwitchNetwork
  type GridNetwork = spade.network.GridNetwork

  /* config */
  type SpadeMap = spade.config.SpadeMap
  /* ------------- Alias (END) ------- **/
}
