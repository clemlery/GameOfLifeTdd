package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.model.dataccess.CsvDAO
import gameoflifetdd.view.ViewMain
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button

class ControlerImportButton(val view: ViewMain, val dao : CsvDAO, val game : GameEngine) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent?) {
        val patterns = dao.searchPatterns()
        view.modal.loadLabels(patterns.map { it.toString() })
        view.showImportModal()
    }

}