package gameoflifetdd.model

class  NextGenerationCalculator {

    fun next(grid: Grid): Grid {

        val next = Grid.empty(
            grid.width,
            grid.height
        )

        for (x in 0 until grid.width) {
            for (y in 0 until grid.height) {

                val cellState = grid.cells[x][y].state

                val neighbors = arrayOf(
                    grid.cells.getOrNull(x-1)?.getOrNull(y-1),
                    grid.cells.getOrNull(x-1)?.getOrNull(y),
                    grid.cells.getOrNull(x-1)?.getOrNull(y+1),
                    grid.cells.getOrNull(x)?.getOrNull(y-1),
                    grid.cells.getOrNull(x)?.getOrNull(y+1),
                    grid.cells.getOrNull(x+1)?.getOrNull(y-1),
                    grid.cells.getOrNull(x+1)?.getOrNull(y),
                    grid.cells.getOrNull(x+1)?.getOrNull(y+1),
                )

                val nbAlive = neighbors.filter { it != null && it.state == CellState.ALIVE }.size

                next.cells[x][y] = when {
                    (cellState == CellState.ALIVE && nbAlive < 2) -> Cell(x, y, CellState.DEAD)
                    (cellState == CellState.ALIVE && nbAlive in 2..3) -> Cell(x, y, CellState.ALIVE)
                    (cellState == CellState.ALIVE && nbAlive in 4..8) -> Cell(x, y, CellState.DEAD)
                    (cellState == CellState.DEAD && nbAlive == 3) -> Cell(x, y, CellState.ALIVE)
                    else -> Cell(x, y, CellState.DEAD)
                }
            }
        }

        return next
    }
}
