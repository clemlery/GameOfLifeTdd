package gameoflifetdd.model.strategy

import gameoflifetdd.model.data.Pattern
import gameoflifetdd.model.data.PatternType
import gameoflifetdd.model.game.CellState

class Context {
    /**
     * Loads a pattern by delegating to the appropriate strategy based on the pattern type.
     *
     * @param pattern the pattern to load
     * @return a 2D matrix of [CellState] representing the decoded pattern
     */
    fun load(pattern: Pattern): MutableList<MutableList<CellState>>{
        return when(pattern.type) {
            PatternType.CELLS -> ConcreteStrategyCells.load(pattern)
            PatternType.RLE -> ConcreteStrategyRle.load(pattern)
            PatternType.MC -> ConcreteStrategyMc.load(pattern)
        }
    }
}