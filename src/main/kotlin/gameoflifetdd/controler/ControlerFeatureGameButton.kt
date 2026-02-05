package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.config.NodeConfig
import gameoflifetdd.view.ViewGame
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button

class ControlerFeatureGameButton(val view : ViewGame, val game : GameEngine) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent?) {
        val buttonId = (event!!.source as Button).id
        when (buttonId) {
            NodeConfig.BUTTON_STOP_ID -> game.stop()
            NodeConfig.BUTTON_RUN_ID -> game.start()
            NodeConfig.BUTTON_REGEN_ID -> game.regenerate()
            NodeConfig.BUTTON_IMPORT_ID -> game.import()
            NodeConfig.BUTTON_EXPORT_ID -> game.export()
        }
    }
}