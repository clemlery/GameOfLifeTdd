package gameoflifetdd.model.strategy

import gameoflifetdd.model.dataccess.Pattern
import gameoflifetdd.model.dataccess.PatternType
import gameoflifetdd.model.game.Cell

class Context {
    fun load(pattern: Pattern): MutableList<MutableList<Cell>> {
        return when(pattern.type) {
            PatternType.CELLS -> ConcreteStrategyCells.load(pattern)
            PatternType.RLE -> ConcreteStrategyRle.load(pattern)
            PatternType.MC -> ConcreteStrategyMc.load(pattern)
        }
    }
}