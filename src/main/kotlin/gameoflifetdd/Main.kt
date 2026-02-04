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

        view.viewHome.fixButtonControler(view.viewHome.getStartButton(), ControlerChangeView(view))
        view.viewGame.fixButtonControler(view.viewGame.getBackButton(), ControlerChangeView(view))
        stage.title = "Game of Life - Conway"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}
