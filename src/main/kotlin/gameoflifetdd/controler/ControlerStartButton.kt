package gameoflifetdd.controler

import gameoflifetdd.config.NodeConfig
import gameoflifetdd.model.Grid
import gameoflifetdd.model.NextGenerationCalculator
import gameoflifetdd.view.CellUI
import gameoflifetdd.view.ViewGame
import gameoflifetdd.view.ViewHome
import javafx.animation.AnimationTimer

import javafx.scene.layout.GridPane

class ControlerStartButton(
    val viewHome: ViewHome,
    val viewGame : ViewGame,
    var modele: Grid
) {

    fun action() {
        val widthTextField = viewHome.getTextFieldInputById(NodeConfig.TEXT_FIELD_WIDTH)
        val heightTextField = viewHome.getTextFieldInputById(NodeConfig.TEXT_FIELD_HEIGHT)
        val nbCellsTextField = viewHome.getTextFieldInputById(NodeConfig.TEXT_FIELD_NB_OF_CELLS)

        val width : Int = widthTextField.text.toInt()
        val height : Int = heightTextField.text.toInt()
        val nbCells : Int = nbCellsTextField.text.toInt()

        val generationCalculator = NextGenerationCalculator()

        modele = Grid.ofAliveCellsRandom(
            nbCells,
            width,
            height
        )

        var gridUI = createGridPaneWithGrid()
        viewGame.setGridCells(gridUI)

        val animator = object : AnimationTimer() {
            override fun handle(now: Long) {
                modele = generationCalculator.next(modele)
                gridUI = createGridPaneWithGrid()
                viewGame.setGridCells(gridUI)
            }
        }
        animator.start()
    }

    private fun gameLoop() {
        TODO()
    }

    private fun createGridPaneWithGrid() : GridPane {
        val gridUI = GridPane()
        for (x in 0 until modele.width) {
            for (y in 0 until modele.height) {
                gridUI.add(CellUI(modele.cellAt(x, y).state), x, y)
            }
        }
        return gridUI
    }
}