package gameoflifetdd.model.game

import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class TestGridUnit {

    @Test
    fun `Test grid empty ok`() {
        Assertions.assertDoesNotThrow { Grid.empty(5, 5) }

    }

    @Test
    fun `Test grid not empty ok`() {
        Assertions.assertDoesNotThrow { Grid.ofAliveCellsPlaced(2 to 2, 3 to 4, gridWidth = 5, gridHeight = 5) }
    }

    @Test
    fun  `Test grid full ok`() {
        Assertions.assertDoesNotThrow { Grid.ofAliveCellsPlaced(
            0 to 0,
            0 to 1,
            1 to 0,
            1 to 1,
            gridWidth = 2,
            gridHeight = 2
            )
        }
    }

    @Test
    fun `Test grid empty Illegal width`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Grid.empty(-2, 2)
        }
    }

    @Test
    fun `Test grid empty Illegal height`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Grid.empty(2, -2)
        }
    }

    @Test
    fun `Test grid ofAliveCells Illegal width`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Grid.ofAliveCellsPlaced(
                0 to 1,
                gridWidth = -2,
                gridHeight = 2
            )
        }
    }

    @Test
    fun `Test grid ofAliveCells Illegal height`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Grid.ofAliveCellsPlaced(
                0 to 1,
                gridWidth = 2,
                gridHeight = -2
            )
        }
    }

    @Test
    fun `Test grid empty Illegal width & height`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Grid.empty(
                gridWidth = 0,
                gridHeight = -0
            )
        }
    }

    @Test
    fun `Test grid ofAliveCells Illegal width & height`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Grid.ofAliveCellsPlaced(
                0 to 1,
                gridWidth = 0,
                gridHeight = -0
            )
        }
    }

    @Test
    fun `Test grid cells outside dimensions`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Grid.ofAliveCellsPlaced(
                5 to 5,
                gridWidth = 5,
                gridHeight = 5
            )
        }
    }

    @Test
    fun `Test grid illegal cell coordinates`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            Grid.ofAliveCellsPlaced(
                -1 to 2,
                gridWidth = 5,
                gridHeight = 5
            )
        }
    }
}
