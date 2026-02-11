package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.model.Cell
import gameoflifetdd.model.CellState
import gameoflifetdd.view.ViewGame
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import kotlin.math.floor

//class ControlerOnCellDragHold(val view: ViewGame, val game: GameEngine) : EventHandler<MouseEvent> {
//
//    override fun handle(event: MouseEvent?) {
//        val cell = view.cellsMatrixUI[0][0]
//
//        val xMouse = event?.x!!
//        val yMouse = event.y
//        val xNewCell : Int = floor(xMouse / cell.width).toInt()
//        val yNewCell : Int = floor(yMouse / cell.height).toInt()
//        game.changeCellAt(
//            xNewCell,
//            yNewCell,
//            Cell(xNewCell, yNewCell, CellState.ALIVE)
//        )
//    }
//}