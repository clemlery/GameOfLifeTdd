package gameoflifetdd.controler.modal

import gameoflifetdd.GameEngine
import gameoflifetdd.model.data.CsvDao
import gameoflifetdd.view.ViewMain
import javafx.event.EventHandler
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent

class ControlerSelectionLabel(
    val view: ViewMain,
    val game: GameEngine,
    val dao: CsvDao
) : EventHandler<MouseEvent> {

    override fun handle(event: MouseEvent?) {
        val label = (event!!.source as Label)
        if (event.clickCount == 2) {
            val pattern = dao.getPattern(label.text) ?: return
            game.import(pattern)
            view.viewGame.setPatternName(pattern.toString())
            view.hideImportModal()
        }
    }
}