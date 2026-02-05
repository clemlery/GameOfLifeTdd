package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import gameoflifetdd.config.NodeConfig
import gameoflifetdd.model.Grid
import gameoflifetdd.view.ViewMain

class ControlerChangeView(var view: ViewMain, val game: GameEngine) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent?) {
        val sourceButton = event?.source as Button
        when (sourceButton.id) {
            NodeConfig.BUTTON_START_ID -> {
                view.changeView(view.viewGame)
                val widthTextField = view.viewHome.getTextFieldInputById(NodeConfig.TEXT_FIELD_WIDTH)
                val heightTextField = view.viewHome.getTextFieldInputById(NodeConfig.TEXT_FIELD_HEIGHT)
                val nbCellsTextField = view.viewHome.getTextFieldInputById(NodeConfig.TEXT_FIELD_NB_OF_CELLS)

                val width: Int = widthTextField.text.toInt()
                val height: Int = heightTextField.text.toInt()
                val nbCells: Int = nbCellsTextField.text.toInt()

                game.init(width, height, nbCells)
                view.viewGame.calculCellPerfectShape(width, height)
            }
            NodeConfig.BUTTON_BACK_ID -> view.changeView(view.viewHome)
        }
    }
}