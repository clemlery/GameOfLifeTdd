package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.model.Cell
import gameoflifetdd.model.CellState
import gameoflifetdd.view.CellUI
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent

class ControlerOnCellClick(val cell : CellUI, val game: GameEngine) : EventHandler<MouseEvent> {

    override fun handle(p0: MouseEvent?) {
        if (game.getCellAt(cell.x, cell.y).state == CellState.ALIVE) {
            game.changeCellAt(cell.x, cell.y, Cell(cell.x, cell.y, CellState.DEAD))
        } else if (game.getCellAt(cell.x, cell.y).state == CellState.DEAD) {
            game.changeCellAt(cell.x, cell.y, Cell(cell.x, cell.y, CellState.ALIVE))
        }
    }

}