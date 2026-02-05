package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.view.ViewGame
import javafx.event.EventHandler
import javafx.scene.control.ProgressBar
import javafx.scene.input.DragEvent

class ControlerSpeedSlider(val view: ViewGame, val game : GameEngine) : EventHandler<DragEvent> {
    override fun handle(event: DragEvent?) {
        game.setSpeed((event!!.source as ProgressBar).progress.toInt())
    }
}