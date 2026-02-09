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

        // VIEW HOME
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

        const val GRID_PADDING = 80.0
        const val GRID_CELLS_CSS_CLASS = "grid-cells"

        const val BUTTON_CONTINUE_ID = "game-button-continue-id"
        const val BUTTON_CLEAR_ID = "game-button-clear-id"
        const val BUTTON_IMPORT_ID = "game-button-import-id"
        const val BUTTON_EXPORT_ID = "game-button-export-id"
        const val BUTTON_REGEN_ID = "game-button-regen-id"

        const val SLIDER_SPEED_ID = "game-progress-bar-speed-id"
        const val SLIDER_NB_CELLS_ID = "game-progress-bar-nb-cells-id"

        const val GRID_CELLS_UP_MARGIN = 60.0
        const val GRID_CELLS_LEFT_MARGIN = 100.0

        // TOP BAR

        const val BUTTON_CLOSE_ID = "top-bar-button-close"
        const val BUTTON_SETTINGS_ID = "top-bar-button-settings"

        const val BUTTONS_WIDTH = 60.0

        // BACKGROUND GAME OF LIFE

        const val CELL_RECT_WIDTH = 50.0
        const val CELL_RECT_HEIGHT = 50.0
        const val BACKGROUND_GRID_WIDTH = 50
        const val BACKGROUND_GRID_HEIGHT = 50
    }
}
