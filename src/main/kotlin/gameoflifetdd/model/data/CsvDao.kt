package gameoflifetdd.model.data

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import gameoflifetdd.config.ModelConfig
import java.io.File
import kotlin.text.uppercase

open class CsvDao(
    source: File
) {
    private val limit = 9
    protected val patterns : MutableList<Pattern> = mutableListOf()
    private var lastSearch = ""
    private var lastOffset = 0

    init {
        loadAllPatterns(source)
    }

    /**
     * Reads all patterns from the given CSV file and populates the internal list.
     *
     * Lines that cannot be parsed (missing fields, unknown type) are silently skipped.
     *
     * @param file the CSV source file with columns for pattern name and type
     */
    private fun loadAllPatterns(file: File) {
        val lines = csvReader {
            delimiter = ','
            quoteChar = '"'
            charset = Charsets.UTF_8.toString()
        }.readAllWithHeader(file)

        lines.forEach { line ->
            try {
                patterns.add(Pattern(
                    line[ModelConfig.CSV_PATTERN_NAME_FIELD]!!,
                    PatternType.valueOf(line[ModelConfig.CSV_TYPE_NAME_FIELD]!!.uppercase())
                ))
            } catch (_: Exception) {
                return@forEach
            }
        }
    }

    /**
     * Searches for patterns whose string representation contains the given search term,
     * and returns a paginated subset starting at the given offset.
     *
     * @param search the search term to filter patterns by; empty string matches all patterns
     * @param offset the index to start the result window from
     * @param updateLastSearch if true, stores the search term and resets the pagination offset
     * @return a list of up to [limit] matching patterns, or an empty list if none match
     */
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

    /**
     * Returns the pattern whose string representation exactly matches the given name.
     *
     * @param name the exact string representation of the pattern (e.g. "glider.rle")
     * @return the matching [Pattern], or null if not found
     */
    fun getPattern(name: String) : Pattern? {
        return patterns.firstOrNull { it.toString() == name }
    }

    /**
     * Returns the current page of patterns based on the last search term and offset.
     *
     * @return a list of patterns for the current page
     */
    fun getCurrentPatterns() : MutableList<Pattern> {
        return searchPatterns(lastSearch, lastOffset)
    }

    /**
     * Advances to the next page of search results.
     *
     * @return the next page of patterns, or null if the current page is the last one
     */
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

    /**
     * Goes back to the previous page of search results.
     *
     * @return the previous page of patterns, or null if already on the first page
     */
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

    /**
     * Returns the current 1-based page number.
     *
     * @return the current page number
     */
    fun getCurrentPage() = lastOffset / 9 + 1
}