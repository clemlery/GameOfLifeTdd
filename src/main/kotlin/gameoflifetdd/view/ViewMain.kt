package gameoflifetdd.view

import gameoflifetdd.config.NodeConfig
import javafx.scene.Node
import javafx.scene.layout.StackPane

class ViewMain() : StackPane() {
    var viewHome = ViewHome()
    var viewGame = ViewGame()

    init {
        this.children.add(viewGame)
        this.styleClass.add(NodeConfig.MAIN_VIEW_CSS_CLASS)
    }

    fun changeView(newView : Node) {
        this.children.clear()
        this.children.add(newView)
    }
}
