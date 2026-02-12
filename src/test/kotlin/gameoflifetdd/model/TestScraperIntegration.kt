package gameoflifetdd.model

import gameoflifetdd.model.dataccess.PatternType
import gameoflifetdd.model.dataccess.Scraper
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestScraperIntegration {
    @Test
    fun `getAllPatterns should return requested number of patterns`() {
        val patterns = Scraper.getAllPatterns(10)
        assertEquals(10, patterns.size)
    }

    @Test
    fun `getAllPatterns should return RLE patterns from GitHub index`() {
        val patterns = Scraper.getAllPatterns(5)
        assertTrue(patterns.all { it.type == PatternType.RLE })
        assertTrue(patterns.all { it.name.isNotBlank() })
    }

    @Test
    fun `getPatternContent should download pattern file`() {
        val patterns = Scraper.getAllPatterns(1)
        val content = Scraper.getPatternContent(patterns[0])
        assertTrue(content != null && content.isNotBlank())
    }
}
