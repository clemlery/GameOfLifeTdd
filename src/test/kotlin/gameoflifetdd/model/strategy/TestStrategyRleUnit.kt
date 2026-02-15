package gameoflifetdd.model.strategy

import gameoflifetdd.model.game.CellState
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class TestStrategyRleUnit {

    private fun rle(header: String, body: String): String {
        return "#N Test\r\n$header\r\n$body"
    }

    private fun rleDefault(body: String): String {
        return rle("x = 3, y = 3, rule = B3/S23", body)
    }

    @Test
    fun `parse should convert o to ALIVE and b to DEAD`() {
        val content = rleDefault("ob")
        val result = ConcreteStrategyRle.parse(content)

        assertEquals(CellState.ALIVE, result[0][0])
        assertEquals(CellState.DEAD, result[0][1])
    }

    @Test
    fun `parse should ignore comment lines starting with hash`() {
        val content = "#C comment\r\n#O author\r\nx = 2, y = 1, rule = B3/S23\r\nob"
        val result = ConcreteStrategyRle.parse(content)

        assertEquals(CellState.ALIVE, result[0][0])
        assertEquals(CellState.DEAD, result[0][1])
    }

    @Test
    fun `parse should throw when rule is not B3 S23`() {
        val content = rle("x = 3, y = 3, rule = B36/S23", "obo")
        assertThrows<IllegalArgumentException> {
            ConcreteStrategyRle.parse(content)
        }
    }

    @Test
    fun `parse should expand digit prefix for alive cells`() {
        val content = rleDefault("3o")
        val result = ConcreteStrategyRle.parse(content)

        assertEquals(CellState.ALIVE, result[0][0])
        assertEquals(CellState.ALIVE, result[0][1])
        assertEquals(CellState.ALIVE, result[0][2])
    }

    @Test
    fun `parse should expand digit prefix for dead cells`() {
        val content = rleDefault("3b")
        val result = ConcreteStrategyRle.parse(content)

        assertEquals(CellState.DEAD, result[0][0])
        assertEquals(CellState.DEAD, result[0][1])
        assertEquals(CellState.DEAD, result[0][2])
    }

    @Test
    fun `parse should handle multiple lines separated by dollar sign`() {
        val content = rleDefault("ob\$bo")
        val result = ConcreteStrategyRle.parse(content)

        val firstLine = result.first { it.contains(CellState.ALIVE) }
        assertEquals(2, firstLine.size)
    }

    @Test
    fun `parse should handle mixed digits and single cells`() {
        val content = rleDefault("2ob")
        val result = ConcreteStrategyRle.parse(content)

        assertEquals(CellState.ALIVE, result[0][0])
        assertEquals(CellState.ALIVE, result[0][1])
    }

    @Test
    fun `parse should handle content without comments`() {
        val content = "x = 2, y = 1, rule = B3/S23\r\nob"
        val result = ConcreteStrategyRle.parse(content)

        assertEquals(CellState.ALIVE, result[0][0])
        assertEquals(CellState.DEAD, result[0][1])
    }

    @Test
    fun `parse should throw when header is missing rule`() {
        val content = "x = 3, y = 3\r\nobo"
        assertThrows<IllegalArgumentException> {
            ConcreteStrategyRle.parse(content)
        }
    }
}
