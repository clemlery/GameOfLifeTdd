package gameoflifetdd.model.data

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import gameoflifetdd.config.ModelConfig
import java.io.File

class CsvDaoBookmark(source: File) : CsvDao(source) {

    private val bookmarks = source

    /**
     * Adds a pattern to the bookmarks, persisting it by appending a row to the CSV file.
     *
     * @param pattern the pattern to bookmark
     */
    fun addBookmark(
        pattern: Pattern
    ) {
        patterns.add(pattern)
        csvWriter {
            delimiter = ','
        }.open(bookmarks, append = true) {
            writeRow(pattern.name, pattern.type.name.lowercase())
        }
    }

    /**
     * Removes a pattern from the bookmarks and rewrites the CSV file without it.
     *
     * @param pattern the pattern to remove from bookmarks
     */
    fun deleteBookmark(
        pattern: Pattern
    ) {
        patterns.remove(pattern)
        var lines = csvReader {
            delimiter = ','
            quoteChar = '"'
            charset = Charsets.UTF_8.toString()
        }.readAllWithHeader(bookmarks)
        lines = lines.filter { it[ModelConfig.CSV_PATTERN_NAME_FIELD] != pattern.name || it[ModelConfig.CSV_TYPE_NAME_FIELD] != pattern.type.toString() }
        val linesToWrite = listOf(listOf("name", "type")) + lines.map { listOf(it[ModelConfig.CSV_PATTERN_NAME_FIELD],it[ModelConfig.CSV_TYPE_NAME_FIELD]) }
        csvWriter {
            delimiter = ','
            charset = Charsets.UTF_8.toString()
        }.writeAll(linesToWrite, bookmarks, false)
    }

    /**
     * Checks whether the given pattern is currently bookmarked.
     *
     * @param pattern the pattern to check
     * @return true if the pattern is in the bookmarks, false otherwise
     */
    fun isBookmarked(
        pattern: Pattern
    ) : Boolean {
        return pattern in patterns
    }
}