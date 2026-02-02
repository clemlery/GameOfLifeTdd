
import model.CellState
import model.Grid
import model.Cell

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
        val grid = Grid.ofAliveCells(
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
    fun `Test cell access outside grid`() {
        val gridWidth = 5
        val gridHeight = 5
        val grid = Grid.ofAliveCells(
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
        val grid = Grid.ofAliveCells(
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