package gameoflifetdd.model.strategy

import gameoflifetdd.model.config.TestConfig
import gameoflifetdd.model.game.CellState
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.io.InputStream
import kotlin.test.assertEquals

class TestStrategyCellsUnit {

    private fun getContentFromFile(file: File): String {
        val inputStream: InputStream = file.inputStream()
        return inputStream.bufferedReader().use { it.readText() }
    }

    @Test
    fun `parse should convert O to ALIVE and dot to DEAD`() {
        val content = "O.\n.O"
        val result = ConcreteStrategyCells.parse(content)

        assertEquals(CellState.ALIVE, result[0][0])
        assertEquals(CellState.DEAD, result[0][1])
        assertEquals(CellState.DEAD, result[1][0])
        assertEquals(CellState.ALIVE, result[1][1])
    }

    @Test
    fun `parse should ignore comment lines starting with exclamation mark`() {
        val content = "!comment\n!another comment\nO.\n.O"
        val result = ConcreteStrategyCells.parse(content)

        assertEquals(2, result.size)
        assertEquals(CellState.ALIVE, result[0][0])
    }

    @Test
    fun `parse should throw on invalid character`() {
        val content = "OX"
        assertThrows<IllegalArgumentException> {
            ConcreteStrategyCells.parse(content)
        }
    }

    @Test
    fun `parse should return correct dimensions for rectangular pattern`() {
        val content = "OOO\n...\nO.O"
        val result = ConcreteStrategyCells.parse(content)

        assertEquals(3, result.size)
        assertEquals(3, result[0].size)
        assertEquals(3, result[1].size)
        assertEquals(3, result[2].size)
    }

    @Test
    fun `parse should return correct dimensions for pattern with empty bloc`() {
        val content = """
            OOOO
            ..OO
            
            
            OOOO
            OOOO
        """.trimIndent()
        val result = ConcreteStrategyCells.parse(content)
        assertEquals(6, result.size)
        assertEquals(4, result[0].size)
        assertEquals(4, result[1].size)
        assertEquals(0, result[2].size)
        assertEquals(0, result[3].size)
        assertEquals(4, result[4].size)
        assertEquals(4, result[5].size)

    }

    @Test
    fun `parse should works on real pattern`() {
        val content = getContentFromFile(TestConfig.CELLS_PATTERN_TEST1)
        val result = ConcreteStrategyCells.parse(content)
        println(result)
    }
}
