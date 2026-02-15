package gameoflifetdd.model.strategy

import gameoflifetdd.model.data.Pattern
import gameoflifetdd.model.data.Scraper
import gameoflifetdd.model.game.CellState

object ConcreteStrategyCells : Strategy {
    override fun load(pattern: Pattern): MutableList<MutableList<CellState>> {
        val body = Scraper.getPatternContent(pattern) ?: throw IllegalArgumentException("Can't find pattern : $pattern")
        return parse(body)
    }

    fun parse(content: String): MutableList<MutableList<CellState>> {
        val lines: MutableList<String> = content.split("\r\n").toMutableList()
        lines.removeAll { it.startsWith("!") }

        val cellsMatrix: MutableList<MutableList<CellState>> = mutableListOf()
        lines.withIndex().forEach { (y, line) ->
            val cellsLine: MutableList<CellState> = mutableListOf()
            line.withIndex().forEach { (x, character) ->
                val state = when (character) {
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