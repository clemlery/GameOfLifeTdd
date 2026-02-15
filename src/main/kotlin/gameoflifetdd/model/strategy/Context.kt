package gameoflifetdd.model.strategy

import gameoflifetdd.model.data.Pattern
import gameoflifetdd.model.data.PatternType
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