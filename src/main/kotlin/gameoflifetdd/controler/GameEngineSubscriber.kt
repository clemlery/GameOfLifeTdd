package gameoflifetdd.controler

import gameoflifetdd.GameObserver
import gameoflifetdd.model.Grid
import gameoflifetdd.view.CellUI
import gameoflifetdd.view.ViewGame

class GameEngineSubscriber(val view: ViewGame) : GameObserver{

    override fun onGridChanged(grid: Grid) {
        for (x in 0 until grid.width) {
            for (y in 0 until grid.height) {
                val currentCellState = grid.cellAt(x, y).state
                view.changeNodeAt(x, y, CellUI(currentCellState))

            }
        }
    }
}