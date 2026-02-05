package gameoflifetdd

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import gameoflifetdd.config.AppConfig
import gameoflifetdd.controler.ControlerChangeView
import gameoflifetdd.controler.GameEngineSubscriber
import gameoflifetdd.view.ViewMain

class Main : Application() {

    override fun start(stage: Stage) {
        val view = ViewMain()

        val scene = Scene(view, AppConfig.INITIAL_WIDTH, AppConfig.INITIAL_HEIGHT)
        try {
            scene.stylesheets.add(
                javaClass.getResource("/css/global.css")!!.toExternalForm()
            )
        } catch (e: Exception) {
            throw e
        }

        val game = GameEngine()
        game.addObserver(GameEngineSubscriber(view.viewGame))

        view.viewHome.fixButtonControler(view.viewHome.getStartButton(), ControlerChangeView(view, game))
        view.viewGame.fixButtonControler(view.viewGame.getBackButton(), ControlerChangeView(view, game))

        stage.title = "Game of Life - Conway"
        stage.scene = scene
        stage.isFullScreen = true
        stage.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}
