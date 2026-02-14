package gameoflifetdd.view

import gameoflifetdd.config.AppConfig
import gameoflifetdd.config.NodeConfig
import javafx.event.ActionEvent
import javafx.event.EventHandler
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
        styleClass.add(NodeConfig.TEXTFIELD_SEARCH_CSS_CLASS)
        font = AppConfig.TEXT_FONT_SMALL
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
        _ -> Label().apply {
            padding = Insets(10.0)
            maxWidth = Double.MAX_VALUE
            styleClass.add(NodeConfig.LABEL_PATTERN_CSS_CLASS)
            font = AppConfig.TEXT_FONT_SMALL
         }
    }
    private val previousButton = Util.createIconButton(
        "/icons/import-modal/previous.svg",
        NodeConfig.BUTTON_PREVIOUS_ID,
        20,
        NodeConfig.BUTTON_ICON_SMALL_CSS_CLASS
    )
    private val nextButton = Util.createIconButton(
        "/icons/import-modal/next.svg",
        NodeConfig.BUTTON_NEXT_ID,
        20,
        NodeConfig.BUTTON_ICON_SMALL_CSS_CLASS
    )
    private val pagination1Button = Button("1").apply {
        styleClass.addAll(NodeConfig.BUTTON_PAGE_CSS_CLASS, NodeConfig.BUTTON_CURRENT_PAGE_CSS_CLASS)
        id = NodeConfig.BUTTON_CURRENT_PAGE_ID
        font = AppConfig.TEXT_FONT_SMALL
    }
    private val pagination2Button = Button("2").apply {
        styleClass.add(NodeConfig.BUTTON_PAGE_CSS_CLASS)
        id = NodeConfig.BUTTON_NEXT_PAGE_ID
        font = AppConfig.TEXT_FONT_SMALL
    }

    init {
        gridContainer.add(
            searchTextField,
            0, 0, 3, 1)
        (patterns.withIndex()).forEach { (i, patternLabel) ->
            val column = i%3
            val row = i/3
            gridContainer.add(patternLabel, column, row+1)
        }
        val buttonContainer = HBox(
            previousButton,
            pagination1Button,
            pagination2Button,
            nextButton
        ).apply {
            alignment = Pos.CENTER
            padding = Insets(NodeConfig.BUTTON_CONTAINER_PADDING)
            spacing = NodeConfig.BUTTON_CONTAINER_SPACING
        }

        val mainContainer = VBox(
            gridContainer,
            buttonContainer
        ).apply {
            spacing = NodeConfig.GRID_CONTAINER_VGAP
        }
        children.add(mainContainer)
        padding = Insets(NodeConfig.MODAL_PADDING)
        maxWidth = NodeConfig.MODAL_WIDTH
        maxHeight = NodeConfig.MODAL_HEIGHT
        alignment = Pos.CENTER
        styleClass.add(NodeConfig.MODAL_IMPORT_CSS_CLASS)
    }

    fun fixButtonControler(buttonToFix : Button, controler : EventHandler<ActionEvent>) {
        buttonToFix.onAction = controler
    }

    fun getButtonById(id : String) : Button {
        return when (id) {
            NodeConfig.BUTTON_PREVIOUS_ID -> previousButton
            NodeConfig.BUTTON_CURRENT_PAGE_ID -> pagination1Button
            NodeConfig.BUTTON_NEXT_PAGE_ID -> pagination2Button
            NodeConfig.BUTTON_NEXT_ID -> nextButton
            else -> throw IllegalArgumentException("Id : $id doesn't exist")
        }
    }

    fun loadLabels(patternsName: List<String>) {
        patterns.zip(patternsName).forEach { (pattern, patternName) ->
            pattern.text = patternName
        }
    }

}