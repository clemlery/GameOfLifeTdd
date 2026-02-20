package gameoflifetdd.view

import gameoflifetdd.config.NodeConfig
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class ViewMain() : StackPane() {

    var viewHome = ViewHome()
    var viewGame = ViewGame()
    val topBar = TopBar()
    val background = CellGrid(
        NodeConfig.BKGRD_CELL_SIZE,
        NodeConfig.COLOR_BACKGROUND
    ).apply {
        toBack()
        parentProperty().addListener { _, _, parent ->
            if (parent is Region) {
                widthProperty().bind(parent.widthProperty())
                heightProperty().bind(parent.heightProperty())
            }
        }
    }
    val modalPatterns = ModalImportPattern().apply { id = NodeConfig.MODAL_PATTERNS_ID }
    val modalBookmarks = ModalImportPattern().apply { id = NodeConfig.MODAL_BOOKMARKS_ID }
    var currentModal = modalPatterns
    val backgroundModal = Rectangle().apply {
        widthProperty().bind(this@ViewMain.widthProperty())
        heightProperty().bind(this@ViewMain.heightProperty())
        fill = Color.rgb(150, 150, 150, 0.5)
    }

    val modalOverlay = StackPane().apply {
        isVisible = false
        children.addAll(backgroundModal, currentModal)
    }

    val mainContainer = VBox(
        topBar,
        viewHome
    )

    init {
        children.addAll(
            background,
            mainContainer,
            modalOverlay
        )
        setAlignment(modalOverlay, Pos.CENTER)
        styleClass.add(NodeConfig.MAIN_VIEW_CSS_CLASS)
    }

    fun changeView(newView : Node) {
        if (newView is ViewHome) {
            children.add(background)
            background.toBack()
        } else if (newView is ViewGame) {
            children.remove(background)
        }
        mainContainer.children.clear()
        mainContainer.children.add(topBar)
        mainContainer.children.add(newView)
    }

    fun showImportModal(modalId: String) {
        modalOverlay.children.remove(currentModal)
        currentModal = when (modalId) {
            NodeConfig.MODAL_PATTERNS_ID -> modalPatterns
            NodeConfig.MODAL_BOOKMARKS_ID -> modalBookmarks
            else -> throw IllegalArgumentException("Id : $modalId doesn't exist")
        }
        modalOverlay.children.add(currentModal)
        modalOverlay.isVisible = true
        modalOverlay.toFront()
        modalOverlay.isMouseTransparent = false
    }

    fun hideImportModal() {
        modalOverlay.isVisible = false
        modalOverlay.isMouseTransparent = true
    }
}
