package gameoflifetdd.view


import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color

class CellGrid(
    var cellWidth: Double,
    var cellHeight: Double,
    val backgroundColor: Color
) : Canvas() {

    var cellsMatrix: Array<Array<Pair<Double, Double>>> = arrayOf()

    fun drawAliveCell(x: Double, y: Double) {
        graphicsContext2D.fill = Color.BLACK
        graphicsContext2D.fillRect(x, y, cellWidth, cellHeight)
    }

    fun drawDeadCell(x: Double, y: Double) {
        graphicsContext2D.fill = backgroundColor
        graphicsContext2D.fillRect(x, y, cellWidth, cellHeight)
    }

    fun clearCanvas() {
        graphicsContext2D.clearRect(0.0, 0.0, width, height)
    }
}