package jeudelavietdd.org.example.view

import javafx.scene.Node
import javafx.scene.layout.StackPane

class ViewMain() : StackPane() {
    var viewHome = ViewHome()
    var viewGame = ViewGame()

    init {
        this.children.add(viewHome)
    }
    
    fun changeView(newView : Node) {
        this.children.clear()
        this.children.add(newView)
    }
}