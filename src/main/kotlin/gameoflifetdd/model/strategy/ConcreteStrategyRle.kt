package gameoflifetdd.model.strategy

import gameoflifetdd.model.data.Pattern
import gameoflifetdd.model.data.Scraper
import gameoflifetdd.model.game.CellState

object ConcreteStrategyRle : Strategy {
    /**
     * Fetches the .rle file content from the remote pattern bank and parses it.
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
     * Parses the content of an RLE-encoded file into a 2D matrix of cell states.
     *
     * Lines starting with '#' are treated as comments and ignored. The first
     * remaining line must contain the rule "B3/S23". The encoding uses 'b' for dead
     * cells, 'o' for alive cells, '$' for end of row, and '!' for end of pattern.
     * Each tag can be prefixed by a run count (e.g. "3o" means three alive cells).
     *
     * @param content the raw RLE file content
     * @return a 2D matrix of [CellState]
     * @throws IllegalArgumentException if the rule is not the default B3/S23
     */
    fun parse(content: String): MutableList<MutableList<CellState>> {
        val lines = content.split("\r\n", "\n").toMutableList()
        lines.removeAll { it.startsWith("#") }

        if (lines.isEmpty() || "rule = B3/S23" !in lines[0]) {
            throw IllegalArgumentException("The rule of this pattern is not the default one")
        }

        lines.removeFirst()
        val encodedContent = lines.joinToString("").replace("!", "") // Enlever le ! de fin

        val pattern = Regex("(\\d*)(b|o|\\$)")
        val matches = pattern.findAll(encodedContent)

        val cellsMatrix: MutableList<MutableList<CellState>> = mutableListOf()
        var currentLine: MutableList<CellState> = mutableListOf()

        matches.forEach { match ->
            val count = match.groupValues[1].ifEmpty { "1" }.toInt() // Si pas de nombre, c'est 1
            val char = match.groupValues[2]

            when (char) {
                "b" -> repeat(count) { currentLine.add(CellState.DEAD) }
                "o" -> repeat(count) { currentLine.add(CellState.ALIVE) }
                "$" -> {
                    cellsMatrix.add(currentLine)
                    currentLine = mutableListOf()
                }
            }
        }
        if (currentLine.isNotEmpty()) {
            cellsMatrix.add(currentLine)
        }

        return cellsMatrix
    }
}