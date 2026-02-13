package gameoflifetdd.view

import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox

class ViewSearchPattern : StackPane() {

    val searchTextField = TextField()

    val patternsContainer = GridPane()

    init {
        val mainContainer = VBox(
            searchTextField,
            patternsContainer
        )

        children.add(mainContainer)
        alignment = Pos.CENTER
    }

}