package gameoflifetdd.view


import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color

class CellGrid(
    var cellSize: Double,
    val backgroundColor: Color
) : Canvas() {

    fun drawAliveCell(x: Double, y: Double) {
        graphicsContext2D.fill = Color.BLACK
        graphicsContext2D.fillRect(x, y, cellSize, cellSize)
    }

    fun drawDeadCell(x: Double, y: Double) {
        graphicsContext2D.fill = backgroundColor
        graphicsContext2D.fillRect(x, y, cellSize, cellSize)
    }

    fun clearCanvas() {
        graphicsContext2D.clearRect(0.0, 0.0, width, height)
    }
}