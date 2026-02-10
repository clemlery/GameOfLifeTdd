package gameoflifetdd.view

import gameoflifetdd.config.NodeConfig
import javafx.scene.canvas.Canvas
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color

class BackgroundGameOfLife(
    val cellWidth: Double = NodeConfig.CELL_RECT_WIDTH,
    val cellHeight: Double = NodeConfig.CELL_RECT_HEIGHT
) : Canvas() {

    var cellsMatrix : Array<Array<Pair<Double, Double>>> = arrayOf()

    init {
        toBack()
        parentProperty().addListener { _, _, parent ->
            if (parent is Region) {
                widthProperty().bind(parent.widthProperty())
                heightProperty().bind(parent.heightProperty())
            }
        }
    }

    fun clearCanvas() {
        graphicsContext2D.clearRect(0.0, 0.0, width, height)
    }

    fun drawAlivedCell(x : Double, y : Double) {
        graphicsContext2D.fill = Color.BLACK
        graphicsContext2D.fillRect(x, y, cellWidth, cellHeight)
    }

    fun drawDeadCell(x : Double, y : Double) {
        graphicsContext2D.fill = NodeConfig.COLOR_BACKGROUND
        graphicsContext2D.fillRect(x, y, cellWidth, cellHeight)
    }
}