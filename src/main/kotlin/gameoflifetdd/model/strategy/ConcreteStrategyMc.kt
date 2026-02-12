package gameoflifetdd.model.strategy

import gameoflifetdd.model.dataccess.Pattern
import gameoflifetdd.model.game.CellState

object ConcreteStrategyMc: Strategy {
    override fun load(pattern: Pattern): MutableList<MutableList<CellState>> {
        TODO("Not yet implemented")
    }
}