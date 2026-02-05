package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.view.ViewGame
import javafx.event.ActionEvent
import javafx.event.EventHandler

class ControlerStopButton(val view : ViewGame,val game : GameEngine) : EventHandler<ActionEvent> {

    override fun handle(action: ActionEvent?) {
        game.stop()
    }

}