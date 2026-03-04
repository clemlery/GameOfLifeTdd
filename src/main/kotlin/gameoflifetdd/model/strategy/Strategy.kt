package gameoflifetdd.model.strategy

import gameoflifetdd.model.data.Pattern
import gameoflifetdd.model.game.CellState

interface Strategy {
    /**
     * Loads a pattern and decodes it into a 2D matrix of cell states.
     *
     * @param pattern the pattern to load and decode
     * @return a 2D matrix of [CellState] representing the pattern content
     */
    fun load(pattern: Pattern): MutableList<MutableList<CellState>>
}