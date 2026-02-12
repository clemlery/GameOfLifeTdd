package gameoflifetdd.model.game

import gameoflifetdd.model.dataccess.Pattern
import gameoflifetdd.model.strategy.Context
import kotlin.math.ceil
import kotlin.properties.Delegates
import kotlin.random.Random

class Grid : GridInterface {
    override lateinit var cells: Array<Array<Cell>>
    override var width by Delegates.notNull<Int>()
    override var height by Delegates.notNull<Int>()

    /**
     * Returns the cell located at the given coordinates in the grid.
     *
     * @param x horizontal coordinate of the cell
     * @param y vertical coordinate of the cell
     * @throws IllegalArgumentException if the specified position is outside the grid
     * @return the cell at position (x, y)
     */
    override fun cellAt(x: Int, y: Int): Cell {
        require(x in 0 ..< this.width && y in 0 ..< this.height)
        return this.cells[x][y]
    }

    fun loadPatternInGrid(pattern: Pattern) {
        val context = Context()
        val cellsMatrixPattern = context.load(pattern)

        var patternWidth = 0
        cellsMatrixPattern.forEach { cellColumn ->
            if (cellColumn.size > patternWidth) {
                patternWidth = cellColumn.size
            }
        }
        val patternHeight : Int = cellsMatrixPattern.size
        val maxGridWidth = patternWidth * 2
        val maxGridHeight = patternHeight * 2

        val newGrid = empty(maxGridWidth, maxGridHeight)

        val gridCenterX = maxGridWidth / 2
        val gridCenterY = maxGridHeight / 2
        val startPointX = gridCenterX - patternWidth / 2
        val startPointY = gridCenterY - patternHeight / 2
        val endPointX = gridCenterX + ceil(patternWidth / 2.0).toInt()
        val endPointY = gridCenterY + ceil(patternHeight / 2.0).toInt()

        for ((i, x) in (startPointX until endPointX).withIndex()) {
            for ((j, y) in (startPointY until endPointY).withIndex()) {
                newGrid.cells[x][y] = Cell(x, y, cellsMatrixPattern[i][j])
            }
        }

        width = newGrid.width
        height = newGrid.height
        cells = newGrid.cells

    }

    companion object {

        /**
         * Creates a grid with living cells placed at random positions.
         *
         * The method generates unique random coordinates for the specified number
         * of living cells. If a duplicate coordinate is generated, a new random
         * position is chosen until all cells are placed.
         *
         * @param numberOfCells number of living cells to place in the grid
         * @param gridWidth width of the grid
         * @param gridHeight height of the grid
         * @throws IllegalArgumentException if numberOfCells is not greater than 2
         *         or is at least half of the total grid size
         * @return a grid of size gridWidth x gridHeight with the specified number
         *         of randomly placed living cells
         */
        fun ofAliveCellsRandom(numberOfCells : Int, gridWidth: Int, gridHeight : Int) : Grid {
            require(numberOfCells > 2)

            val rdmCoos : ArrayDeque<Pair<Int, Int>> = ArrayDeque(Array(numberOfCells) { Random.nextInt(0, gridWidth - 1)}
                .zip(Array(numberOfCells) { Random.nextInt(0, gridHeight - 1)}.toList()))

            val grid = empty(gridWidth, gridHeight)
            val cooAlreadyUsed : MutableSet<Pair<Int, Int>> = mutableSetOf()

            while (rdmCoos.isNotEmpty()) {
                val rdmCoo = rdmCoos.firstOrNull() ?: break
                rdmCoos.removeFirst()
                if (rdmCoo !in cooAlreadyUsed) {
                    grid.cells[rdmCoo.first][rdmCoo.second] = Cell(
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

            val grid = Grid()
            grid.width = gridWidth
            grid.height = gridHeight
            grid.cells = Array(gridWidth) { x ->
                Array(gridHeight) { y ->
                    Cell(x, y, CellState.DEAD)
                }
            }

            return grid
        }
    }
}
