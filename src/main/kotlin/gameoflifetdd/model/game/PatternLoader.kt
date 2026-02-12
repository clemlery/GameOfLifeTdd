package gameoflifetdd.model.game

import gameoflifetdd.model.dataccess.Pattern
import gameoflifetdd.model.dataccess.Scraper

object PatternLoader {
    fun loadFromCellsType(pattern: Pattern) : MutableList<MutableList<Cell>> {
        val body = Scraper.getPatternContent(pattern) ?: throw IllegalArgumentException("Can't find pattern : $pattern")
        val content : MutableList<String> = body.split("/n").toMutableList()
        content.removeAll { it.startsWith("!") }

        val cellsMatrix : MutableList<MutableList<Cell>> = mutableListOf()
        var state: CellState
        (content.withIndex()).forEach { (y, line) ->
            val cellsLine : MutableList<Cell> = mutableListOf()
            (line.withIndex()).forEach { (x, character) ->
                state = when (character) {
                    'O' -> CellState.ALIVE
                    '.' -> CellState.DEAD
                    else -> throw IllegalArgumentException(
                        "Invalid character '$character' at position ($x,$y)"
                    )
                }
                cellsLine.add(Cell(x, y, state))
            }
            cellsMatrix.add(cellsLine)
        }
        return cellsMatrix
    }

    fun loadFromRleType() {
        TODO()
    }

    fun loadFromMcType() {
        TODO()
    }
}