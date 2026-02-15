package gameoflifetdd.config

import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color

class NodeConfig {
    companion object {
        // VIEW MAIN
        const val MAIN_VIEW_CSS_CLASS = "main-container"
        val COLOR_BACKGROUND: Color = Color.web("#D9D9D9")

        // VIEW HOME

        const val VIEW_HOME_CSS_CLASS = "view-home-container"

        const val BUTTON_STANDARD_CSS_CLASS = "button-standard"

        const val BUTTON_START_ID = "start-game-button"
        const val BUTTON_BACK_ID = "back-home-button"

        const val TEXT_FIELD_WIDTH = "text-field-input-width"
        const val TEXT_FIELD_HEIGHT = "text-field-input-height"
        const val TEXT_FIELD_NB_OF_CELLS = "text-field-input-nb-cells"

        const val TEXT_FIELDS_MAX_WIDTH = 250.0
        const val TEXT_FIELDS_MIN_HEIGHT = 40.0
        const val BUTTON_MAX_WIDTH = 250.0

        val TEXT_FIELDS_BACKGROUND_COLOR : Background = Background(
            BackgroundFill(
                Color.WHITE,
                CornerRadii.EMPTY,
                Insets.EMPTY
            )
        )

        // VIEW GAME

        const val VIEW_GAME_CSS_CLASS = "view-game-container"

        const val GRID_PADDING = 80.0
        const val GRID_CELLS_CSS_CLASS = "grid-cells"
        val GRID_BACKGROUND_COLOR : Color = Color.WHITE

        const val BUTTON_CONTINUE_ID = "game-button-continue-id"
        const val BUTTON_CLEAR_ID = "game-button-clear-id"
        const val BUTTON_IMPORT_ID = "game-button-import-id"
        const val BUTTON_EXPORT_ID = "game-button-export-id"
        const val BUTTON_REGEN_ID = "game-button-regen-id"

        const val SLIDER_SPEED_ID = "game-progress-bar-speed-id"
        const val SLIDER_NB_CELLS_ID = "game-progress-bar-nb-cells-id"

        const val GRID_CELLS_UP_MARGIN = 30.0
        const val GRID_CELLS_LEFT_MARGIN = 100.0

        const val CENTER_CONTAINER_ID = "game-left-container-id"
        const val RIGHT_CONTAINER_ID = "game-right-container-id"

        // TOP BAR

        const val TOP_BAR_CSS_CLASS = "top-bar-container"

        const val BUTTON_CLOSE_ID = "top-bar-button-close"
        const val BUTTON_SETTINGS_ID = "top-bar-button-settings"

        const val BUTTONS_WIDTH = 60.0

        // BACKGROUND GAME OF LIFE

        const val BKGRD_CELL_SIZE = 50.0
        const val BACKGROUND_GRID_WIDTH = 50
        const val BACKGROUND_GRID_HEIGHT = 50

        // MODAL PATTERN
        const val MODAL_WIDTH = 600.0
        const val MODAL_HEIGHT = 250.0
        const val MODAL_PADDING = 20.0
        const val GRID_CONTAINER_HGAP = 20.0
        const val GRID_CONTAINER_VGAP = 15.0
        const val BUTTON_CONTAINER_PADDING = 5.0
        const val BUTTON_CONTAINER_SPACING = 15.0

        const val MODAL_IMPORT_CSS_CLASS = "modal"
        const val TEXTFIELD_SEARCH_CSS_CLASS = "search-textfield"
        const val BUTTON_SEARCH_CSS_CLASS = "search-button"
        const val LABEL_PATTERN_CSS_CLASS = "pattern-label"
        const val LABEL_PAGE_CSS_CLASS = "page-label"
        const val BUTTON_CURRENT_PAGE_CSS_CLASS = "current-button-page"

        const val BUTTON_SEARCH_ID = "modal-import-button-search"
        const val BUTTON_PREVIOUS_ID = "modal-import-button-previous"
        const val BUTTON_PREVIOUS_PAGE_ID = "modal-import-button-previous-page"
        const val BUTTON_NEXT_PAGE_ID = "modal-import-button-next-page"
        const val BUTTON_NEXT_ID = "modal-import-button-next"
        const val BUTTON_ICON_SMALL_CSS_CLASS = "icon-button-small"
    }
}
