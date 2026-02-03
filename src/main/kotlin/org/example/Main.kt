package jeudelavietdd.org.example

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import jeudelavietdd.org.example.view.ViewHomePage

class Main : Application() {
    override fun start(stage: Stage) {
        val view = ViewHomePage()
        val scene = Scene(view, AppConfig.INITIAL_WIDTH, AppConfig.INITIAL_HEIGHT)
        stage.title = "Game of Life - Conway"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}
