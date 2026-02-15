package gameoflifetdd.view

import gameoflifetdd.config.NodeConfig
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.HBox

class TopBar : HBox() {

    private val closeButton = Util.createIconButton("/icons/top-bar/close.svg", NodeConfig.BUTTON_CLOSE_ID)
    private val settingsButton = Util.createIconButton("/icons/top-bar/settings.svg", NodeConfig.BUTTON_SETTINGS_ID)

    init {
        children.addAll(
            settingsButton,
                    closeButton
        )
        alignment = Pos.TOP_RIGHT
        styleClass.add(NodeConfig.TOP_BAR_CSS_CLASS)
    }

    fun fixButtonControler(buttonToFix : Button, controler : EventHandler<ActionEvent>) {
        buttonToFix.onAction = controler
    }

    fun getButtonById(id : String) : Button {
        return when(id) {
            NodeConfig.BUTTON_CLOSE_ID -> closeButton
            NodeConfig.BUTTON_SETTINGS_ID -> settingsButton
            else -> throw IllegalArgumentException()
        }
    }
}