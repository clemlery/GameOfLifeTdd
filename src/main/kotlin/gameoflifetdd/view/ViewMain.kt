package gameoflifetdd.view

import gameoflifetdd.config.NodeConfig
import javafx.scene.Node
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox

class ViewMain() : StackPane() {

    var viewHome = ViewHome()
    var viewGame = ViewGame()
    val viewSearchPattern = ModalImportPattern()
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

    val mainContainer = VBox(
        topBar,
        viewHome
    )

    init {
        children.add(background)
        children.add(mainContainer)
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

    fun displayModal(modal : Node) {
        mainContainer.children.add(modal)
    }
}
