package gameoflifetdd.config

import javafx.scene.text.Font

class AppConfig {
    companion object {
        // Window size when launched
        const val INITIAL_WIDTH = 720.0
        const val INITIAL_HEIGHT = 480.0

        val TITLE_FONT : Font = loadFont("/fonts/PlayfairDisplay-Regular.ttf", 60.0)
        val TEXT_FONT : Font = loadFont("/fonts/BungeeShade-Regular.ttf", 20.0)


    }
}
