package gameoflifetdd.controler.modal

import gameoflifetdd.GameEngine
import gameoflifetdd.model.data.CsvDAO
import gameoflifetdd.view.ViewMain
import javafx.event.ActionEvent
import javafx.event.EventHandler

class ControlerImportButton(val view: ViewMain, val dao : CsvDAO, val game : GameEngine) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent?) {
        val patterns = dao.searchPatterns()
        view.modal.loadLabels(patterns.map { it.toString() })
        view.showImportModal()
    }

}