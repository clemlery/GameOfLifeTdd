package gameoflifetdd.view

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox

class ModalImportPattern : StackPane() {

    private val searchTextField = TextField()

    private val patternsContainer = GridPane()

    private val patterns = Array<Label>(9) { i -> Label("pattern${i+1}") }

    private val previousButton = Button("previous")
    private val nextButton = Button("next")
    private val pagination1Button = Button("1")
    private val pagination2Button = Button("2")
    private val pagination3Button = Button("3")


    init {
        patternsContainer.add(searchTextField, 0, 0, 3, 1)
        (patterns.withIndex()).forEach { (i, patternLabel) ->
            val column = i%3
            val row = i/3
            patternsContainer.add(patternLabel, column, row)
        }
        val mainContainer = VBox(
            searchTextField,
            patternsContainer,
            HBox(
                previousButton,
                pagination1Button,
                pagination2Button,
                pagination3Button,
                nextButton
            )
        )

        children.add(mainContainer)
        alignment = Pos.CENTER
    }

}