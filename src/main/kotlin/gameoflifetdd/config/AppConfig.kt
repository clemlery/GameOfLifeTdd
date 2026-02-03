package gameoflifetdd.config

import javafx.scene.text.Font

class AppConfig {
    companion object {
        // Window size when launched
        const val INITIAL_WIDTH = 720.0
        const val INITIAL_HEIGHT = 480.0

        val TITLE_FONT : Font = Font.font("Roboto", 60.0)
        val TEXT_FONT : Font = Font.font("Roboto", 30.0)


    }
}
