package gameoflifetdd.controler

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import gameoflifetdd.config.NodeConfig
import gameoflifetdd.view.ViewMain

class ControlerChangeView(var view : ViewMain) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent?) {
        val sourceButton = event?.source as Button
        when (sourceButton.id) {
            NodeConfig.BUTTON_START_ID -> view.changeView(view.viewGame)
            NodeConfig.BUTTON_BACK_ID -> view.changeView(view.viewHome)
        }
    }
}
