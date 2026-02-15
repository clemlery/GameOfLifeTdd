package gameoflifetdd

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import gameoflifetdd.config.AppConfig
import gameoflifetdd.config.NodeConfig
import gameoflifetdd.controler.ControlerChangeView
import gameoflifetdd.controler.game.ControlerFeatureGameButton
import gameoflifetdd.controler.modal.ControlerHideModal
import gameoflifetdd.controler.modal.ControlerImportButton
import gameoflifetdd.controler.modal.ControlerModalsButton
import gameoflifetdd.controler.game.ControlerNbCellsSlider
import gameoflifetdd.controler.game.ControlerOnCellClick
import gameoflifetdd.controler.game.ControlerOnCellDragHold
import gameoflifetdd.controler.modal.ControlerSearchTextField
import gameoflifetdd.controler.game.ControlerSpeedSlider
import gameoflifetdd.controler.topbar.ControlerTopBarButton
import gameoflifetdd.controler.GameEngineSubscriberBackground
import gameoflifetdd.controler.modal.ControlerSelectionLabel
import gameoflifetdd.model.data.CsvDAO
import gameoflifetdd.view.ViewMain

class Main : Application() {

    override fun start(stage: Stage) {

        val view = ViewMain()
        val dao = CsvDAO(
            AppConfig.SOURCE_FILE,
            AppConfig.BOOKMARKED_FILE
        )

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
            view.modalOverlay.stylesheets.add(
                javaClass.getResource("/css/views/view-modal-style.css")!!.toExternalForm()
            )
        } catch (e: Exception) {
            throw e
        }

        val game = GameEngine()
        game.addObserver(GameEngineSubscriberBackground(view.background))
        game.init(
            NodeConfig.BACKGROUND_GRID_WIDTH,
            NodeConfig.BACKGROUND_GRID_HEIGHT,
            NodeConfig.BACKGROUND_GRID_WIDTH * NodeConfig.BACKGROUND_GRID_HEIGHT / 4
        )
        game.start()

        view.topBar.fixButtonControler(view.topBar.getButtonById(NodeConfig.BUTTON_CLOSE_ID), ControlerTopBarButton())
        view.topBar.fixButtonControler(view.topBar.getButtonById(NodeConfig.BUTTON_SETTINGS_ID), ControlerTopBarButton())

        view.viewHome.fixButtonControler(view.viewHome.getStartButton(), ControlerChangeView(view, game))
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_BACK_ID), ControlerChangeView(view, game))
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_IMPORT_ID),
            ControlerImportButton(view, dao, game)
        )
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_CONTINUE_ID), ControlerFeatureGameButton(view.viewGame, game))
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_CLEAR_ID), ControlerFeatureGameButton(view.viewGame, game))
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_REGEN_ID), ControlerFeatureGameButton(view.viewGame, game))
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_EXPORT_ID), ControlerFeatureGameButton(view.viewGame, game))

        view.viewGame.fixSliderControler(view.viewGame.getSliderById(NodeConfig.SLIDER_SPEED_ID),
            ControlerSpeedSlider(game))
        view.viewGame.fixSliderControler(view.viewGame.getSliderById(NodeConfig.SLIDER_NB_CELLS_ID),
            ControlerNbCellsSlider(game))

        view.viewGame.cellGrid.onMouseClicked = ControlerOnCellClick(view.viewGame.cellGrid, game)
        view.viewGame.cellGrid.onMouseDragged = ControlerOnCellDragHold(view.viewGame.cellGrid, game)

        view.backgroundModal.onMouseClicked = ControlerHideModal(view)
        view.modal.fixButtonControler(view.modal.getButtonById(NodeConfig.BUTTON_PREVIOUS_ID), ControlerModalsButton(view, dao))
        view.modal.fixButtonControler(view.modal.getButtonById(NodeConfig.BUTTON_PREVIOUS_PAGE_ID), ControlerModalsButton(view, dao))
        view.modal.fixButtonControler(view.modal.getButtonById(NodeConfig.BUTTON_NEXT_ID), ControlerModalsButton(view, dao))
        view.modal.fixButtonControler(view.modal.getButtonById(NodeConfig.BUTTON_NEXT_PAGE_ID), ControlerModalsButton(view, dao))
        view.modal.fixTextFieldSearchControler(ControlerSearchTextField(view, dao))
        view.modal.fixLabelsControler(ControlerSelectionLabel(view, game, dao))

        stage.title = "Game of Life - Conway"
        stage.scene = scene
        stage.isFullScreen = true
        stage.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}
