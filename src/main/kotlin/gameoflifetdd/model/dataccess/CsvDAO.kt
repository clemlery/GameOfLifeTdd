package gameoflifetdd.model.dataccess

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import gameoflifetdd.config.ModelConfig
import java.io.File

object CsvDAO {
    fun load(
        file: File,
        limit: Int
    ): List<Pattern> {
        val lines = csvReader {
            delimiter = ','
            quoteChar = '"'
            charset = Charsets.UTF_8.toString()
        }.readAllWithHeader(file).drop(1)

        val patterns = mutableListOf<Pattern>()
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
        return patterns
    }

    fun write(
        file: File,
        pattern: Pattern
    ) {
        csvWriter {
            delimiter = ','
        }.open(file, append = true) {
            writeRow(pattern.name, pattern.type.name.lowercase())
        }
    }
}