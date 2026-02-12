package gameoflifetdd.model.strategy

import gameoflifetdd.model.dataccess.Pattern
import gameoflifetdd.model.dataccess.Scraper
import gameoflifetdd.model.game.CellState

object ConcreteStrategyCells : Strategy {
    override fun load(pattern: Pattern): MutableList<MutableList<CellState>> {
        val body = Scraper.getPatternContent(pattern) ?: throw IllegalArgumentException("Can't find pattern : $pattern")
        val content : MutableList<String> = body.split("/n").toMutableList()
        content.removeAll { it.startsWith("!") }

        val cellsMatrix : MutableList<MutableList<CellState>> = mutableListOf()
        var state: CellState
        (content.withIndex()).forEach { (y, line) ->
            val cellsLine : MutableList<CellState> = mutableListOf()
            (line.withIndex()).forEach { (x, character) ->
                state = when (character) {
                    'O' -> CellState.ALIVE
                    '.' -> CellState.DEAD
                    else -> throw IllegalArgumentException(
                        "Invalid character '$character' at position ($x,$y)"
                    )
                }
                cellsLine.add(state)
            }
            cellsMatrix.add(cellsLine)
        }
        return cellsMatrix
    }
}