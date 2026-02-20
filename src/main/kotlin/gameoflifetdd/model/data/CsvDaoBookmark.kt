package gameoflifetdd.model.data

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import gameoflifetdd.config.ModelConfig
import java.io.File

class CsvDaoBookmark(source: File) : CsvDao(source) {

    private val bookmarks = source

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
}