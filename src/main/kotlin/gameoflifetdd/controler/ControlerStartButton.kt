package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.config.NodeConfig
import gameoflifetdd.model.Grid
import gameoflifetdd.model.NextGenerationCalculator
import gameoflifetdd.view.CellUI
import gameoflifetdd.view.ViewGame
import gameoflifetdd.view.ViewHome
import javafx.animation.AnimationTimer
import javafx.event.ActionEvent
import javafx.event.EventHandler

import javafx.scene.layout.GridPane

class ControlerStartButton(
    val viewHome: ViewHome,
    var game: GameEngine
) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent?) {
        val widthTextField = viewHome.getTextFieldInputById(NodeConfig.TEXT_FIELD_WIDTH)
        val heightTextField = viewHome.getTextFieldInputById(NodeConfig.TEXT_FIELD_HEIGHT)
        val nbCellsTextField = viewHome.getTextFieldInputById(NodeConfig.TEXT_FIELD_NB_OF_CELLS)

        val width: Int = widthTextField.text.toInt()
        val height: Int = heightTextField.text.toInt()
        val nbCells: Int = nbCellsTextField.text.toInt()

        game.init(width, height, nbCells)
        game.start()
    }
}