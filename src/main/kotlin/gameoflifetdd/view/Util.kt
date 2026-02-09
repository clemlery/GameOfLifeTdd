package gameoflifetdd.view

import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.control.Button
import javafx.scene.shape.SVGPath
import kotlin.math.max

object Util {
    fun getIconGroup(path : String) : Group {
        val svgText = javaClass
            .getResource(path)
            ?.readText()
            ?: error("SVG not found: $path")

        val paths = Regex("d=\"([^\"]+)\"")
            .findAll(svgText)
            .map { match -> match.groupValues[1] }
            .toList()

        val svgNodes = paths.map { d ->
            SVGPath().apply {
                content = d
                styleClass.add("button-icon")
            }
        }

        val group = Group().apply {
            children.addAll(svgNodes)
        }

        val bounds = group.boundsInLocal

        val scaleFactor = 40 / max(bounds.width, bounds.height)

        group.scaleX = scaleFactor
        group.scaleY = scaleFactor

        return group
    }

    fun createIconButton(path: String, buttonId : String?): Button {

        val group = getIconGroup(path)

        val button = Button().apply {
            isPickOnBounds = true
            graphic = group
            alignment = Pos.CENTER
            styleClass.add("icon-button")
            id = buttonId
        }

        return button
    }

    fun changeButtonIcon(path : String, button: Button) : Button {
        val group = getIconGroup(path)

        return button.apply {
            graphic = group
        }
    }
}