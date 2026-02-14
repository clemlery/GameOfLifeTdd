package gameoflifetdd.view

import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.control.Button
import javafx.scene.shape.SVGPath
import kotlin.math.max

object Util {
    fun getIconGroup(path : String, size: Int, cssClass: String = "button-icon") : Group {
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
                styleClass.add(cssClass)
            }
        }

        val group = Group().apply {
            children.addAll(svgNodes)
        }

        val bounds = group.boundsInLocal

        val scaleFactor = size / max(bounds.width, bounds.height)

        group.scaleX = scaleFactor
        group.scaleY = scaleFactor

        return group
    }

    fun createIconButton(
        path: String,
        buttonId: String?,
        size: Int = 40,
        cssClass: String = "icon-button"  // ← Nouveau paramètre
    ): Button {
        val group = getIconGroup(path, size, cssClass)

        return Button().apply {
            isPickOnBounds = true
            graphic = group
            alignment = Pos.CENTER
            this.styleClass.add(cssClass)  // ← Utiliser le paramètre
            id = buttonId
        }
    }

    fun changeButtonIcon(path : String, button: Button, size : Int = 40) : Button {
        val group = getIconGroup(path, size)

        return button.apply {
            graphic = group
        }
    }
}