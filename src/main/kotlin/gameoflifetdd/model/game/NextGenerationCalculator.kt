package gameoflifetdd.model.game

class NextGenerationCalculator {

    /**
     * Computes the next generation of the Game of Life from the given grid.
     *
     * Applies the standard rules for each cell:
     * - A living cell with exactly 2 or 3 living neighbors survives.
     * - A dead cell with exactly 3 living neighbors becomes alive.
     * - All other cells die or stay dead.
     *
     * Cells outside the grid boundaries are considered dead (finite grid, no wrapping).
     *
     * @param grid the current generation grid
     * @return a new grid representing the next generation
     */
    fun next(grid: Grid): Grid {
        val next = Grid()
        next.width = grid.width
        next.height = grid.height
        next.cells = Array(grid.width) { x ->
            Array(grid.height) { y ->
                var nbAlive = 0
                for (dx in -1..1) {
                    for (dy in -1..1) {
                        if (dx == 0 && dy == 0) continue
                        val neighbor = grid.cells.getOrNull(x + dx)?.getOrNull(y + dy)
                        if (neighbor?.state == CellState.ALIVE) nbAlive++
                    }
                }
                val alive = nbAlive == 3 || (grid.cells[x][y].state == CellState.ALIVE && nbAlive == 2)
                Cell(x, y, if (alive) CellState.ALIVE else CellState.DEAD)
            }
        }
        return next
    }
}
