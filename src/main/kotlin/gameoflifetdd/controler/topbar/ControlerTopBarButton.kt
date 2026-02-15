package gameoflifetdd.controler.topbar

import gameoflifetdd.config.NodeConfig
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button

class ControlerTopBarButton() : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent?) {
        val buttonId = (event!!.source as Button).id
        when (buttonId) {
            NodeConfig.BUTTON_CLOSE_ID -> {
                Platform.exit()
            }
            NodeConfig.BUTTON_SETTINGS_ID -> TODO()
            else -> throw IllegalArgumentException()
        }
    }

}