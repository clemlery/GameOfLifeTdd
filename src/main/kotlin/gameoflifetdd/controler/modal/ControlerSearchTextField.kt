package gameoflifetdd.controler.modal

import gameoflifetdd.model.dataccess.CsvDAO
import gameoflifetdd.view.ViewMain
import javafx.event.ActionEvent
import javafx.event.EventHandler

class ControlerSearchTextField(val view: ViewMain, val dao: CsvDAO) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent?) {
        val patternSearched = view.modal.getTextFromSearch()
        val patterns = dao.searchPatterns(
            search = patternSearched,
            updateLastSearch = true
        )
        view.modal.loadLabels(patterns.map { it.toString() })
        view.modal.loadPagination(dao.getCurrentPage())
    }

}