package gameoflifetdd.model.data

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import gameoflifetdd.config.ModelConfig
import java.io.File
import kotlin.text.uppercase

class CsvDAO(
    source: File,
    val bookmarks: File
) {

    private val limit = 9
    private val patterns : MutableList<Pattern> = mutableListOf()
    private var lastSearch = ""
    private var lastOffset = 0

    init {
        loadAllPatterns(source)
    }

    private fun loadAllPatterns(file: File) {
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
        offset: Int = 0,
        updateLastSearch : Boolean = false
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
        for (i in offset until stop) {
            patternsFound.add(patternsUsed[i])
        }

        if (updateLastSearch) {
            lastSearch = search
            lastOffset = 0
        }

        return patternsFound
    }

    fun getPattern(patternName: String) : Pattern? {
        return patterns.firstOrNull() { it.toString() == patternName }
    }

    fun next() : MutableList<Pattern>? {
        val currentSearch = searchPatterns(lastSearch, lastOffset)
        if (currentSearch.size == limit) {
            lastOffset += 9
            return searchPatterns(
                lastSearch,
                lastOffset
            )
        } else {
            return null
        }
    }

    fun previous() : MutableList<Pattern>? {
        if (lastOffset == 0) return null
        else {
            lastOffset -= 9
            return searchPatterns(
                lastSearch,
                lastOffset
            )
        }
    }

    fun getCurrentPage() = lastOffset / 9 + 1

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