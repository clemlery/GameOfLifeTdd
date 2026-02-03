
import gameoflifetdd.model.CellState
import gameoflifetdd.model.Grid
import gameoflifetdd.model.NextGenerationCalculator
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class TestNextGenerationCalculator {

    private lateinit var calculator: NextGenerationCalculator
    private lateinit var grid: Grid

    @BeforeTest
    fun setUp() {
        calculator = NextGenerationCalculator()
    }


    @Test
    fun `alive cell with less than 2 neighbors dies`() {
        grid = Grid.ofAliveCellsPlaced(2 to 2, gridWidth = 5, gridHeight = 5)

        val next = calculator.next(grid)

        assertEquals(CellState.DEAD, next.cellAt(2, 2).state)
    }

    @Test
    fun `alive cells with less than 2 neighbors dies`() {
        grid = Grid.ofAliveCellsPlaced(
            2 to 2,
            2 to 1,
            gridWidth = 5,
            gridHeight = 5
        )

        val next = calculator.next(grid)

        assertEquals(CellState.DEAD, next.cellAt(2, 2).state)
        assertEquals(CellState.DEAD, next.cellAt(2, 1).state)
    }

    @Test
    fun `alive cells with 2 or 3 neighbors survive`() {
        grid = Grid.ofAliveCellsPlaced(
            2 to 1,
            2 to 2,
            1 to 2,
            1 to 3,
            gridWidth = 4,
            gridHeight = 4
        )

        val next = calculator.next(grid)

        assertEquals(CellState.ALIVE, next.cellAt(2, 1).state)
        assertEquals(CellState.ALIVE, next.cellAt(2, 2).state)
        assertEquals(CellState.ALIVE, next.cellAt(1, 2).state)
        assertEquals(CellState.ALIVE, next.cellAt(1, 3).state)
    }

    @Test
    fun `alive cell with 4-8 neighbors dies of overpopulation`() {
        grid = Grid.ofAliveCellsPlaced(
            2 to 1,
            2 to 2,
            1 to 2,
            1 to 3,
            3 to 1,
            3 to 2,
            3 to 3,
            gridWidth = 4,
            gridHeight = 4
        )

        val next = calculator.next(grid)

        assertEquals(CellState.DEAD, next.cellAt(2, 2).state)
    }

    @Test
    fun `dead cell with 3 neighbors reborn`() {
        grid = Grid.ofAliveCellsPlaced(
            2 to 1,
            2 to 2,
            1 to 2,
            1 to 3,
            gridWidth = 4,
            gridHeight = 4
        )

        val next = calculator.next(grid)

        assertEquals(CellState.ALIVE, next.cellAt(1, 1).state)
        assertEquals(CellState.ALIVE, next.cellAt(2, 3).state)
    }

    @Test
    fun `dead cell with 0-2 neighbors stay dead`() {
        grid = Grid.ofAliveCellsPlaced(
            1 to 2,
            2 to 2,
            gridWidth = 4,
            gridHeight = 4
        )

        val next = calculator.next(grid)

        assertEquals(CellState.DEAD, next.cellAt(0, 2).state)
        assertEquals(CellState.DEAD, next.cellAt(1, 1).state)
        assertEquals(CellState.DEAD, next.cellAt(2, 1).state)
        assertEquals(CellState.DEAD, next.cellAt(3, 2).state)
        assertEquals(CellState.DEAD, next.cellAt(2, 3).state)
        assertEquals(CellState.DEAD, next.cellAt(1, 3).state)
    }


    @Test
    fun `dead cell with 4-8 neighbors stay dead`() {
        grid = Grid.ofAliveCellsPlaced(
            1 to 1,
            2 to 1,
            3 to 1,
            1 to 2,
            2 to 3,
            3 to 2,
            gridWidth = 4,
            gridHeight = 4
        )

        val next = calculator.next(grid)

        assertEquals(CellState.DEAD, next.cellAt(2, 2).state)
    }
}
