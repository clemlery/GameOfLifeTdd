package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.GameObserver
import gameoflifetdd.model.game.CellState
import gameoflifetdd.model.game.Grid
import gameoflifetdd.view.CellGrid
import javafx.scene.effect.GaussianBlur

class GameEngineSubscriberBackground(val component: CellGrid) : GameObserver {

    override fun onGridInit(game: GameEngine) {
        component.clearCanvas()
        component.effect = GaussianBlur(30.0)
    }

    override fun onGridChanged(grid: Grid) {
        grid.cells.forEach { cellsColumn ->
            cellsColumn.forEach { cell ->
                val cellX : Double = cell.x * component.cellSize
                val cellY : Double = cell.y * component.cellSize
                if (cell.state == CellState.ALIVE) component.drawAliveCell(cellX, cellY)
                else component.drawDeadCell(cellX, cellY)
            }
        }
    }
}