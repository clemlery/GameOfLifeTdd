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
import gameoflifetdd.model.data.CsvDao
import gameoflifetdd.model.data.CsvDaoBookmark
import gameoflifetdd.view.ViewMain

class Main : Application() {

    override fun start(stage: Stage) {

        val view = ViewMain()
        val dao = CsvDao(
            AppConfig.SOURCE_FILE
        )
        val daoBookmark = CsvDaoBookmark(
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

        val controlerTopBar = ControlerTopBarButton()
        view.topBar.fixButtonControler(view.topBar.getButtonById(NodeConfig.BUTTON_CLOSE_ID), controlerTopBar)
        view.topBar.fixButtonControler(view.topBar.getButtonById(NodeConfig.BUTTON_SETTINGS_ID), controlerTopBar)

        val controlerChangeView = ControlerChangeView(view, game)
        view.viewHome.fixButtonControler(view.viewHome.getStartButton(), controlerChangeView)
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_BACK_ID), controlerChangeView)
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_IMPORT_ID),
            ControlerImportButton(view, dao, game, NodeConfig.MODAL_PATTERNS_ID)
        )
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_SHOW_BOOKMARKS_ID),
            ControlerImportButton(view, daoBookmark, game, NodeConfig.MODAL_BOOKMARKS_ID)
        )

        val controlerGameButton = ControlerFeatureGameButton(view.viewGame, game, dao, daoBookmark)
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_CONTINUE_ID), controlerGameButton)
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_CLEAR_ID), controlerGameButton)
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_REGEN_ID), controlerGameButton)
        view.viewGame.fixButtonControler(view.viewGame.getButtonById(NodeConfig.BUTTON_BOOKMARK_ID), controlerGameButton)

        view.viewGame.fixSliderControler(view.viewGame.getSliderById(NodeConfig.SLIDER_SPEED_ID),
            ControlerSpeedSlider(game))
        view.viewGame.fixSliderControler(view.viewGame.getSliderById(NodeConfig.SLIDER_NB_CELLS_ID),
            ControlerNbCellsSlider(game))

        view.viewGame.cellGrid.onMouseClicked = ControlerOnCellClick(view.viewGame.cellGrid, game)
        view.viewGame.cellGrid.onMouseDragged = ControlerOnCellDragHold(view.viewGame.cellGrid, game)

        view.backgroundModal.onMouseClicked = ControlerHideModal(view)
        view.modalPatterns.fixButtonControler(view.modalPatterns.getButtonById(NodeConfig.BUTTON_PREVIOUS_ID), ControlerModalsButton(view, dao))
        view.modalPatterns.fixButtonControler(view.modalPatterns.getButtonById(NodeConfig.BUTTON_NEXT_ID), ControlerModalsButton(view, dao))
        view.modalPatterns.fixTextFieldSearchControler(ControlerSearchTextField(view, dao))
        view.modalPatterns.fixLabelsControler(ControlerSelectionLabel(view, game, dao))

        view.modalBookmarks.fixButtonControler(view.modalPatterns.getButtonById(NodeConfig.BUTTON_PREVIOUS_ID), ControlerModalsButton(view, daoBookmark))
        view.modalBookmarks.fixButtonControler(view.modalPatterns.getButtonById(NodeConfig.BUTTON_NEXT_ID), ControlerModalsButton(view, daoBookmark))
        view.modalBookmarks.fixTextFieldSearchControler(ControlerSearchTextField(view, daoBookmark))
        view.modalBookmarks.fixLabelsControler(ControlerSelectionLabel(view, game, daoBookmark))

        stage.title = "Game of Life - Conway"
        stage.scene = scene
        stage.isFullScreen = true
        stage.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}
