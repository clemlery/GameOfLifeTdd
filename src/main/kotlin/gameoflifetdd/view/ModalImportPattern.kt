package gameoflifetdd.view

import gameoflifetdd.config.NodeConfig
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color

class ModalImportPattern : StackPane() {

    private val searchTextField = TextField().apply {
        promptText = "Type Something ..."
        maxWidth = Double.MAX_VALUE
    }
    private val gridContainer = GridPane().apply {
        hgap = NodeConfig.GRID_CONTAINER_HGAP
        vgap = NodeConfig.GRID_CONTAINER_VGAP
        columnConstraints.addAll(
            ColumnConstraints().apply {
                percentWidth = 33.33
                hgrow = Priority.ALWAYS  // Permet de grandir
            },
            ColumnConstraints().apply { percentWidth = 33.33; hgrow = Priority.ALWAYS },
            ColumnConstraints().apply { percentWidth = 33.33; hgrow = Priority.ALWAYS }
        )
    }

    private val patterns = Array(9) {
        i -> Label("pattern${i+1}").apply {
            padding = Insets(10.0)
            background = Background(
                BackgroundFill(
                    Color.WHITE,
                    CornerRadii.EMPTY,
                    Insets.EMPTY
                )
            )
            maxWidth = Double.MAX_VALUE
        }
    }

    private val previousButton = Button("previous")
    private val nextButton = Button("next")
    private val pagination1Button = Button("1")
    private val pagination2Button = Button("2")
    private val pagination3Button = Button("3")


    init {
        gridContainer.add(searchTextField, 0, 0, 3, 1)
        (patterns.withIndex()).forEach { (i, patternLabel) ->
            val column = i%3
            val row = i/3
            gridContainer.add(patternLabel, column, row+1)
        }

        val mainContainer = VBox(
            gridContainer,
            HBox(
                previousButton,
                pagination1Button,
                pagination2Button,
                pagination3Button,
                nextButton
            )
        )
        children.add(mainContainer)
        padding = Insets(NodeConfig.MODAL_PADDING)
        maxWidth = NodeConfig.MODAL_WIDTH
        maxHeight = NodeConfig.MODAL_HEIGHT
        background = Background(
            BackgroundFill(
                NodeConfig.COLOR_BACKGROUND,
                CornerRadii.EMPTY,
                Insets.EMPTY
            )
        )
        alignment = Pos.CENTER
    }

}