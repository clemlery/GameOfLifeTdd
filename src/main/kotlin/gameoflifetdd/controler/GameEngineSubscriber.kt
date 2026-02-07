package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.GameObserver
import gameoflifetdd.model.Cell
import gameoflifetdd.model.CellState
import gameoflifetdd.model.Grid
import gameoflifetdd.view.CellUI
import gameoflifetdd.view.ViewGame
import javafx.scene.layout.GridPane

class GameEngineSubscriber(val view: ViewGame) : GameObserver{

    override fun onGridInit(game : GameEngine) {
        val gridWidth = game.getGridWidth()
        val gridHeight = game.getGridHeight()

        val cellsMatrix = mutableListOf<Array<CellUI>>()
        for (x in 0 until gridWidth) {
            val newCellsColumn = mutableListOf<CellUI>()
            for (y in 0 until gridHeight) {
                val newCell = CellUI(game.getCellAt(x, y).state, x, y)
                view.addCellUIToGrid(x, y, newCell)
                newCell.apply {
                    val parent : GridPane = (parent as GridPane)
                    heightProperty().bind(parent.widthProperty().divide(2).divide(gridHeight))
                    widthProperty().bind(parent.widthProperty().divide(2).divide(gridWidth))
                    fixCellControler(ControlerOnCellClick(this, game))
                }
                newCellsColumn.add(newCell)
            }
            cellsMatrix.add(newCellsColumn.toTypedArray())
        }
        view.cellsMatrixUI = cellsMatrix.toTypedArray()
    }

    override fun onGridChanged(grid: Grid) {
        view.cellsMatrixUI.forEach { cellsUIColumns ->
            cellsUIColumns.forEach { cellUI ->
                cellUI.updateCellColor(grid.cellAt(cellUI.x, cellUI.y).state)
            }
        }
    }
}