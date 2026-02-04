package gameoflifetdd

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import gameoflifetdd.config.AppConfig
import gameoflifetdd.controler.ControlerChangeView
import gameoflifetdd.model.Grid
import gameoflifetdd.view.ViewMain

class Main : Application() {
    override fun start(stage: Stage) {
        val view = ViewMain()
        val scene = Scene(view, AppConfig.INITIAL_WIDTH, AppConfig.INITIAL_HEIGHT)
        val modele : Grid = Grid()
        view.viewHome.fixButtonControler(view.viewHome.getStartButton(), ControlerChangeView(view, modele))
        view.viewGame.fixButtonControler(view.viewGame.getBackButton(), ControlerChangeView(view, modele))

        stage.title = "Game of Life - Conway"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}
