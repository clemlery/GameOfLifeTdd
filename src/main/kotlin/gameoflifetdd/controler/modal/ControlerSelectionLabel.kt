package gameoflifetdd.controler.modal

import gameoflifetdd.GameEngine
import gameoflifetdd.model.data.CsvDAO
import gameoflifetdd.view.ViewMain
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent

class ControlerSelectionLabel(
    val view: ViewMain,
    val game: GameEngine,
    val dao: CsvDAO
) : EventHandler<MouseEvent> {

    override fun handle(event: MouseEvent?) {
        val label = (event!!.source as Label)
        if (event.clickCount == 2) {
            val pattern = dao.getPattern(label.text) ?: return
            game.import(pattern)
            view.hideImportModal()
        }
    }

}