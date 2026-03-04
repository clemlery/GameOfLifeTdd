package gameoflifetdd.model.strategy

import gameoflifetdd.model.data.Pattern
import gameoflifetdd.model.game.CellState

object ConcreteStrategyMc: Strategy {
    /**
     * Loads a .mc (Macrocell) pattern file. Not yet implemented.
     *
     * @param pattern the pattern to load
     * @return a 2D matrix of [CellState]
     * @throws NotImplementedError always, as this strategy is not yet implemented
     */
    override fun load(pattern: Pattern): MutableList<MutableList<CellState>> {
        TODO("Not yet implemented")
    }
}