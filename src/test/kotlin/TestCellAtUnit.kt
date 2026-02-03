
import gameoflifetdd.model.Cell
import gameoflifetdd.model.CellState
import gameoflifetdd.model.Grid
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TestCellAtUnit {

    lateinit var grid : Grid

    @BeforeEach
    fun gridSetUp() {
        grid = Grid.ofAliveCellsPlaced(
            0 to 1,
            1 to 1,
            gridWidth = 3,
            gridHeight = 3
        )
    }

    @ParameterizedTest()
    @CsvSource(
        "-2, 2",
        "-2, -2",
        "2, -2",
    )
    fun `Test illegal argument`(x : Int, y : Int) {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            grid.cellAt(x, y)
        }
    }


    @Test
    fun  `Test correct argument`() {
        Assertions.assertAll(
            { Assertions.assertEquals(grid.cellAt(0, 1), Cell(0, 1, CellState.ALIVE))},
            { Assertions.assertEquals(grid.cellAt(1, 1), Cell(1, 1, CellState.ALIVE))},
            { Assertions.assertEquals(grid.cellAt(2, 2), Cell(2, 2, CellState.DEAD))}
        )
    }
}
