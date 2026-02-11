package gameoflifetdd.model.game

import gameoflifetdd.model.dataccess.Pattern

interface GridInterface {
    val cells : Array<Array<Cell>>
    val width : Int
    val height : Int

    /**
     * Returns the cell located at the given coordinates in the grid.
     *
     * @param x horizontal coordinate of the cell
     * @param y vertical coordinate of the cell
     * @throws IllegalArgumentException if the specified position is outside the grid
     * @return the cell at position (x, y)
     */
    fun cellAt(x: Int, y: Int): Cell

    fun loadFromPattern(pattern: Pattern) {
        TODO()
    }
}