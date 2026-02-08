package gameoflifetdd.view

import gameoflifetdd.config.NodeConfig
import javafx.scene.Node
import javafx.scene.layout.StackPane

class ViewMain(val topBar: TopBar) : StackPane() {
    var viewHome = ViewHome()
    var viewGame = ViewGame()


    init {
        this.children.add(topBar)
        this.children.add(viewHome)
        this.styleClass.add(NodeConfig.MAIN_VIEW_CSS_CLASS)
    }

    fun changeView(newView : Node) {
        this.children.clear()
        this.children.add(topBar)
        this.children.add(newView)
    }
}
