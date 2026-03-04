package gameoflifetdd.model.strategy

import gameoflifetdd.model.data.Pattern
import gameoflifetdd.model.data.Scraper
import gameoflifetdd.model.game.CellState

object ConcreteStrategyCells : Strategy {
    /**
     * Fetches the .cells file content from the remote pattern bank and parses it.
     *
     * @param pattern the pattern to load
     * @return a 2D matrix of [CellState] representing the pattern
     * @throws IllegalArgumentException if the pattern cannot be found remotely
     */
    override fun load(pattern: Pattern): MutableList<MutableList<CellState>> {
        val body = Scraper.getPatternContent(pattern) ?: throw IllegalArgumentException("Can't find pattern : $pattern")
        return parse(body)
    }

    /**
     * Parses the content of a .cells file into a 2D matrix of cell states.
     *
     * Lines starting with '!' are treated as comments and ignored.
     * 'O' maps to [CellState.ALIVE], '.' maps to [CellState.DEAD].
     *
     * @param content the raw .cells file content
     * @return a 2D matrix of [CellState]
     * @throws IllegalArgumentException if an unexpected character is encountered
     */
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