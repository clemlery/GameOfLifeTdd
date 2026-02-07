package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.GameObserver
import gameoflifetdd.model.Grid
import gameoflifetdd.view.CellUI
import gameoflifetdd.view.ViewGame

class GameEngineSubscriber(val view: ViewGame) : GameObserver{

    override fun onGridInit(game : GameEngine) {
        val gridWidth = game.getGridWidth()
        val gridHeight = game.getGridHeight()

        view.clearGrid()

        val cellsMatrix = mutableListOf<Array<CellUI>>()
        for (x in 0 until gridWidth) {
            val newCellsColumn = mutableListOf<CellUI>()
            for (y in 0 until gridHeight) {
                val newCell = CellUI(game.getCellAt(x, y).state, x, y)
                view.addCellUIToGrid(x, y, newCell)
                newCell.apply {
                    fixCellControler(ControlerOnCellClick(this, game))
                }
                newCellsColumn.add(newCell)
            }
            cellsMatrix.add(newCellsColumn.toTypedArray())
        }
        view.cellsMatrixUI = cellsMatrix.toTypedArray()
        view.updateCellsShape(view.width)
        view.setSliderNbCellsMax((gridWidth * gridHeight) * 0.75)
    }

    override fun onGridChanged(grid: Grid) {
        view.cellsMatrixUI.forEach { cellsUIColumns ->
            cellsUIColumns.forEach { cellUI ->
                cellUI.updateColor(grid.cellAt(cellUI.x, cellUI.y).state)
            }
        }
    }
}