package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.GameObserver
import gameoflifetdd.config.NodeConfig
import gameoflifetdd.model.game.CellState
import gameoflifetdd.model.game.Grid
import gameoflifetdd.view.ViewGame
import javafx.application.Platform

class GameEngineSubscriber(val view: ViewGame) : GameObserver{

    override fun onGridInit(game : GameEngine) {
        val gridWidth = game.getGridWidth()
        val gridHeight = game.getGridHeight()

        view.clearGrid()

        if (view.height > 0) {
            updateCellShape(view.height, game)
            draw(game.getGrid())
        } else {
            // NOTE : replace Platform.runlater by Task
            Platform.runLater {
                updateCellShape(view.height, game)
                draw(game.getGrid())
            }
        }

        view.setSliderNbCellsMax((gridWidth * gridHeight) * 0.75)
    }

    override fun onGridChanged(grid: Grid) {
        draw(grid)
    }


    fun updateCellShape(newHeight: Double, game: GameEngine) {
        val availableHeight: Double = newHeight - NodeConfig.GRID_CELLS_UP_MARGIN * 2
        view.cellGrid.cellSize  = availableHeight / game.getGridWidth()
        view.cellGrid.width = view.cellGrid.cellSize * game.getGridWidth()
        view.cellGrid.height = view.cellGrid.cellSize * game.getGridHeight()
    }

    fun draw(grid: Grid) {
        grid.cells.forEach { cellsColumn ->
            cellsColumn.forEach { cell ->
                val cellX : Double = cell.x * view.cellGrid.cellSize
                val cellY : Double = cell.y * view.cellGrid.cellSize
                if (cell.state == CellState.ALIVE) view.cellGrid.drawAliveCell(cellX, cellY)
                else view.cellGrid.drawDeadCell(cellX, cellY)
            }
        }
    }
}