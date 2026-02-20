package gameoflifetdd.config

import javafx.scene.text.Font
import java.io.File

class AppConfig {
    companion object {
        // Window size when launched
        var INITIAL_WIDTH = 720.0
        var INITIAL_HEIGHT = 480.0

        val TITLE_FONT : Font = loadFont("/fonts/PlayfairDisplay-Regular.ttf", 60.0)
        val TEXT_FONT_MEDIUM : Font = loadFont("/fonts/CarroisGothic-Regular.ttf", 40.0)
        val TEXT_FONT_SMALL : Font = loadFont("/fonts/CarroisGothic-Regular.ttf", 25.0)

        val SOURCE_FILE = File(AppConfig::class.java.getResource("/data/patterns.csv")!!.toURI())
        val BOOKMARKED_FILE = File(System.getProperty("user.dir"), "bookmarked.csv")

    }
}
