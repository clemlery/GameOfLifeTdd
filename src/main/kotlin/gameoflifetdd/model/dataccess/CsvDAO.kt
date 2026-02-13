package gameoflifetdd.model.dataccess

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import gameoflifetdd.config.ModelConfig
import java.io.File
import kotlin.text.uppercase

class CsvDAO(
    source: File,
    val bookmarks: File
) {

    private val patterns : MutableList<Pattern> = mutableListOf()

    init {
        loadAllPatterns(source)
    }

    fun loadAllPatterns(file: File) {
        val lines = csvReader {
            delimiter = ','
            quoteChar = '"'
            charset = Charsets.UTF_8.toString()
        }.readAllWithHeader(file).drop(1)

        lines.forEach { line ->
            try {
                patterns.add(Pattern(
                    line.get(ModelConfig.CSV_PATTERN_NAME_FIELD)!!,
                    PatternType.valueOf(line.get(ModelConfig.CSV_TYPE_NAME_FIELD)!!.uppercase())
                ))
            } catch (_: Exception) {
                return@forEach
            }
        }
    }

    fun searchPatterns(
        search: String = "",
        limit: Int = 9,
        offset: Int = 0
    ): MutableList<Pattern> {
        val patternsFound = mutableListOf<Pattern>()
        val patternsUsed = if (search != "") {
            patterns.filter { it.toString().contains(search) }
        } else {
            patterns
        }
        if (patternsUsed.isEmpty()) return mutableListOf()
        val stop = if (offset+limit > patternsUsed.size) {
            patternsUsed.size
        } else {
            offset+limit
        }
        for (i in offset+1 until stop) {
            patternsFound.add(patternsUsed[i])
        }
        return patterns
    }

    fun addToBookmarks(
        pattern: Pattern
    ) {
        csvWriter {
            delimiter = ','
        }.open(bookmarks, append = true) {
            writeRow(pattern.name, pattern.type.name.lowercase())
        }
    }
}