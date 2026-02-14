package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.config.NodeConfig
import gameoflifetdd.view.ViewGame
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button

class ControlerFeatureGameButton(val view : ViewGame, val game : GameEngine) : EventHandler<ActionEvent> {

    var toggle = false

    override fun handle(event: ActionEvent?) {
        val buttonId = (event!!.source as Button).id
        when (buttonId) {
            NodeConfig.BUTTON_CONTINUE_ID -> {
                if (toggle) game.stop()
                else game.start()
                toggle = !toggle
                view.toggleIcon(toggle)
            }
            NodeConfig.BUTTON_CLEAR_ID -> game.clear()
            NodeConfig.BUTTON_REGEN_ID -> game.regenerate()
            NodeConfig.BUTTON_EXPORT_ID -> game.export()
        }
    }
}