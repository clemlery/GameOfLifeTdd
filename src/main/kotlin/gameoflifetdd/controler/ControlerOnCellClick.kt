package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.model.game.Cell
import gameoflifetdd.model.game.CellState
import gameoflifetdd.view.CellGrid
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import kotlin.math.floor

class ControlerOnCellClick(val cellGrid: CellGrid, val game: GameEngine) : EventHandler<MouseEvent> {

    override fun handle(event: MouseEvent?) {
        val xMouse = event?.x!!
        val yMouse = event.y
        val xNewCell : Int = floor(xMouse / cellGrid.cellSize).toInt()
        val yNewCell : Int = floor(yMouse / cellGrid.cellSize).toInt()
        if (game.getCellAt(xNewCell, yNewCell).state == CellState.ALIVE) {
            game.changeCellAt(xNewCell, yNewCell, Cell(xNewCell, yNewCell, CellState.DEAD))
        } else if (game.getCellAt(xNewCell, yNewCell).state == CellState.DEAD) {
            game.changeCellAt(xNewCell, yNewCell, Cell(xNewCell, yNewCell, CellState.ALIVE))
        }
    }

}