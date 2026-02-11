package gameoflifetdd.model

import gameoflifetdd.model.game.Cell
import gameoflifetdd.model.game.CellState
import gameoflifetdd.model.game.Grid
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class TestGridIntegration {

    @Test
    fun `Test empty all cells dead`() {
        val gridWidth = 5
        val gridHeight = 5
        val grid : Grid = Grid.empty(5, 5)
        val oracle = CellState.DEAD
        for (i in 0 until gridWidth) {
            for (j in 0 until gridHeight) {
                Assertions.assertEquals(oracle, grid.cellAt(i, j).state)
            }
        }
    }

    @Test
    fun `Test ofAliveCells cells alive`() {
        val gridWidth = 5
        val gridHeight = 5
        val grid = Grid.ofAliveCellsPlaced(
            1 to 4,
            2 to 1,
            1 to 1,
            gridWidth = gridWidth,
            gridHeight = gridHeight
        )

        val oracle = CellState.ALIVE
        Assertions.assertEquals(oracle, grid.cellAt(1, 4).state)
        Assertions.assertEquals(oracle, grid.cellAt(2, 1).state)
        Assertions.assertEquals(oracle, grid.cellAt(1, 1).state)
    }

    @Test
    fun `Test ofAliveCellsRandom create grid with right number of cells`() {
        val oracle = 5
        val grid = Grid.ofAliveCellsRandom(oracle, 5, 5)

        var countNumberOfCells = 0
        grid.cells.forEach { cellsColumn ->
            countNumberOfCells += cellsColumn.count { it.state == CellState.ALIVE }
        }

        Assertions.assertEquals(oracle, countNumberOfCells)
    }

    @Test
    fun `Raise exception if numberOfCells is lower than 2`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Grid.ofAliveCellsRandom(2, 5, 5)
        }
    }

    @Test
    fun `Raise exception if numberOfCells is bigger than grid aira divided by 2`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Grid.ofAliveCellsRandom(13, 5, 5)
        }
    }

    @Test
    fun `Test cell access outside grid`() {
        val gridWidth = 5
        val gridHeight = 5
        val grid = Grid.ofAliveCellsPlaced(
            1 to 4,
            2 to 1,
            1 to 1,
            gridWidth = gridWidth,
            gridHeight = gridHeight
        )
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            grid.cellAt(0, 6)
        }
    }

    @Test
    fun `Test cell access inside grid`() {
        val gridWidth = 5
        val gridHeight = 5
        val grid = Grid.ofAliveCellsPlaced(
            1 to 4,
            2 to 1,
            1 to 1,
            gridWidth = gridWidth,
            gridHeight = gridHeight
        )
        val oracle = Cell(1, 4, CellState.ALIVE)
        Assertions.assertEquals(oracle, grid.cellAt(1, 4))
    }
}
