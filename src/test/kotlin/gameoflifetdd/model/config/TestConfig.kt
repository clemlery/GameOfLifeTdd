package gameoflifetdd.model.config

import gameoflifetdd.config.AppConfig
import java.io.File

class TestConfig {
    companion object {
        val CELLS_PATTERN_TEST1 = File(TestConfig::class.java.getResource("/patterns/104p32.cells")!!.toURI())
        val CELLS_PATTERN_TEST2 = File(TestConfig::class.java.getResource("/patterns/103p4h1v0.cells")!!.toURI())
        val CELLS_PATTERN_TEST3 = File(TestConfig::class.java.getResource("/patterns/104p177.cells")!!.toURI())
    }
}