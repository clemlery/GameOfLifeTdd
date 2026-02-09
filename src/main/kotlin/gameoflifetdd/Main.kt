package gameoflifetdd

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import gameoflifetdd.config.AppConfig
import gameoflifetdd.config.NodeConfig
import gameoflifetdd.controler.ControlerChangeView
import gameoflifetdd.controler.ControlerFeatureGameButton
import gameoflifetdd.controler.ControlerNbCellsSlider
import gameoflifetdd.controler.ControlerOnCellDragHold
import gameoflifetdd.controler.ControlerSpeedSlider
import gameoflifetdd.controler.ControlerTopBarButton
import gameoflifetdd.controler.GameEngineSubscriber
import gameoflifetdd.view.TopBar
import gameoflifetdd.view.ViewMain

class Main : Application() {

    override fun start(stage: Stage) {
        val topBar = TopBar()

        topBar.fixButtonControler(topBar.getButtonById(NodeConfig.BUTTON_CLOSE_ID), ControlerTopBarButton())
        topBar.fixButtonControler(topBar.getButtonById(NodeConfig.BUTTON_SETTINGS_ID), ControlerTopBarButton())

        val view = ViewMain(topBar)

        val scene = Scene(view, AppConfig.INITIAL_WIDTH, AppConfig.INITIAL_HEIGHT)

        try {
            scene.stylesheets.add(
                javaClass.getResource("/css/global.css")!!.toExternalForm()
            )
            view.viewHome.stylesheets.add(
                javaClass.getResource("/css/views/view-home-style.css")!!.toExternalForm()
            )
            view.viewGame.stylesheets.add(
                javaClass.getResource("/css/views/view-game-style.css")!!.toExternalForm()
            )

        } catch (e: Exception) {
            throw e
        }

        val game = GameEngine()
        game.addObserver(GameEngineSubscriber(view.viewGame))


        view.viewHome.fixButtonControler(view.viewHome.getStartButton(), ControlerChangeView(view, game))
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_BACK_ID), ControlerChangeView(view, game))

        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_CONTINUE_ID), ControlerFeatureGameButton(view.viewGame, game))
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_CLEAR_ID), ControlerFeatureGameButton(view.viewGame, game))
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_REGEN_ID), ControlerFeatureGameButton(view.viewGame, game))
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_IMPORT_ID), ControlerFeatureGameButton(view.viewGame, game))
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_EXPORT_ID), ControlerFeatureGameButton(view.viewGame, game))

        view.viewGame.fixSliderControler(view.viewGame.getSliderById(NodeConfig.SLIDER_SPEED_ID),
            ControlerSpeedSlider(game))

        view.viewGame.fixSliderControler(view.viewGame.getSliderById(NodeConfig.SLIDER_NB_CELLS_ID),
            ControlerNbCellsSlider(game))

        view.viewGame.fixGridPaneControler(view.viewGame.getGridCells(), ControlerOnCellDragHold(view.viewGame, game))

        stage.title = "Game of Life - Conway"
        stage.scene = scene
        stage.isFullScreen = true
        stage.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}
