package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.model.Cell
import gameoflifetdd.model.CellState
import gameoflifetdd.view.CellUI
import javafx.event.ActionEvent
import javafx.event.EventHandler

class ControlerOnCellClick(val cell : CellUI, val game: GameEngine) : EventHandler<ActionEvent> {

    override fun handle(p0: ActionEvent?) {

        if (game.getCellAt(cell.x, cell.y).state == CellState.ALIVE) {
            game.changeCellAt(cell.x, cell.y, Cell(cell.x, cell.y, CellState.DEAD))
        }
    }

}