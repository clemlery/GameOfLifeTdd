package gameoflifetdd.model

import kotlin.random.Random

class Grid(
    val cells: Array<Array<Cell>>,
    val width : Int,
    val height : Int,
) {
    /**
     * Returns the cell located at the given coordinates in the grid.
     *
     * @param x horizontal coordinate of the cell
     * @param y vertical coordinate of the cell
     * @throws IllegalArgumentException if the specified position is outside the grid
     * @return the cell at position (x, y)
     */
    fun cellAt(x: Int, y: Int): Cell {
        require(x in 0 ..< this.width && y in 0 ..< this.height)
        return this.cells[x][y]
    }

    companion object {

        fun ofAliveCellsRandom(numberOfCells : Int, gridWidth: Int, gridHeight : Int) : Grid {
            require(numberOfCells < gridWidth * gridHeight / 2 && numberOfCells > 2)

            val rdmCoos : ArrayDeque<Pair<Int, Int>> = ArrayDeque(Array(numberOfCells) { Random.nextInt(0, gridWidth - 1)}
                .zip(Array(numberOfCells) { Random.nextInt(0, gridHeight - 1)}.toList()))

            val grid = empty(gridWidth, gridHeight)
            val cooAlreadyUsed : MutableSet<Pair<Int, Int>> = mutableSetOf()

            while (rdmCoos.isNotEmpty()) {
                val rdmCoo = rdmCoos.firstOrNull() ?: break
                if (rdmCoo !in cooAlreadyUsed) {
                    grid.cells[rdmCoo.first][rdmCoo.first] = Cell(
                        rdmCoo.first,
                        rdmCoo.second,
                        CellState.ALIVE
                    )
                    cooAlreadyUsed.add(rdmCoo)
                } else {
                    rdmCoos.add(Pair(
                        Random.nextInt(0, gridWidth - 1),
                        Random.nextInt(0, gridHeight - 1)
                    ))
                }
            }

            return grid
        }

        /**
         * Creates an initial grid from the coordinates of living cells.
         *
         * All unspecified cells are considered dead.
         * This method is primarily intended for testing purposes to easily define
         * initial configurations for the Game of Life.
         *
         * @param aliveCells list of coordinates (x, y) of living cells
         * @param gridWidth width of the grid
         * @param gridHeight height of the grid
         * @throws IllegalArgumentException if the height or width are not acceptable values (e.g., negative or too large)
         * @return a grid of size gridWidth x gridHeight containing the corresponding living and dead cells
         */
        fun ofAliveCellsPlaced(vararg aliveCells: Pair<Int, Int>, gridWidth : Int, gridHeight : Int): Grid {
            require(aliveCells.isNotEmpty() && gridWidth > 0 && gridHeight > 0)

            val grid = empty(gridWidth, gridHeight)

            aliveCells.forEach { (x, y) ->
                require(x in 0..<gridWidth && y in 0 ..<gridHeight)
                grid.cells[x][y] = Cell(x, y, CellState.ALIVE)
            }

            return grid
        }

        /**
         * Creates an empty grid of size gridWidth by gridHeight.
         *
         * @param gridWidth width of the grid
         * @param gridHeight height of the grid
         * @throws IllegalArgumentException if the height or width are not acceptable values (e.g., negative or too large)
         * @return a grid containing dead cells of size gridWidth x gridHeight
         */
        fun empty(gridWidth: Int, gridHeight: Int) : Grid {
            require(gridWidth > 0 && gridHeight > 0)

            return Grid(
                cells = Array(gridWidth) { x ->
                    Array(gridHeight) { y ->
                        Cell(x, y, CellState.DEAD)
                    }
                },
                gridWidth,
                gridHeight
            )
        }
    }
}
