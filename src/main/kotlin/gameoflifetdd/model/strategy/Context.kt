package gameoflifetdd.model.strategy

import gameoflifetdd.model.dataccess.Pattern
import gameoflifetdd.model.dataccess.PatternType
import gameoflifetdd.model.game.Cell
import gameoflifetdd.model.game.CellState

class Context {
    fun load(pattern: Pattern): MutableList<MutableList<CellState>>{
        return when(pattern.type) {
            PatternType.CELLS -> ConcreteStrategyCells.load(pattern)
            PatternType.RLE -> ConcreteStrategyRle.load(pattern)
            PatternType.MC -> ConcreteStrategyMc.load(pattern)
        }
    }
}