package gameoflifetdd.view

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import gameoflifetdd.config.NodeConfig

class ViewGame : StackPane() {

    private val gridCells = GridPane()
    private val backButton = Button("back").apply {
        id = NodeConfig.BUTTON_BACK_ID
    }

    init {
        this.children.addAll(
            gridCells,
            backButton
        )
        this.alignment = Pos.CENTER
    }

    fun fixButtonControler(buttonToFix : Button, controler : EventHandler<ActionEvent>) {
        buttonToFix.onAction = controler
    }

    fun getBackButton() : Button {
        return backButton
    }

}
