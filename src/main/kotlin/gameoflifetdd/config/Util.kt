package gameoflifetdd.config

import javafx.scene.text.Font

fun loadFont(path : String, fontSize : Double): Font {
    return Font.loadFont(gameoflifetdd.config.AppConfig::class.java.getResourceAsStream(path), fontSize)
        ?: throw IllegalArgumentException("Font not found")
}