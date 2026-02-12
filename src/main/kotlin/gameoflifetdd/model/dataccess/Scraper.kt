package gameoflifetdd.model.dataccess

import gameoflifetdd.config.ModelConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.Locale.getDefault

object Scraper {
    private val client = OkHttpClient()

    fun getAllPatterns(limit: Int) : List<Pattern> {
        val request = Request.Builder()
            .url(ModelConfig.PATTERNS_INDEX_URL)
            .build()

        val json = client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return emptyList()
            response.body.string()
        }

        // index.json structure: [{"conwaylife": ["file1.rle", "file2.rle", ...]}]
        val filenames = parseFilenamesFromIndex(json)

        val patterns = mutableListOf<Pattern>()
        for (filename in filenames) {
            if (patterns.size >= limit) break

            val dotIndex = filename.lastIndexOf('.')
            if (dotIndex == -1) continue

            val patternName = filename.substring(0, dotIndex)
            val fileExtension = filename.substring(dotIndex + 1)

            val patternType = try {
                PatternType.valueOf(fileExtension.uppercase(getDefault()))
            } catch (_: Exception) {
                continue
            }
            patterns.add(Pattern(patternName, patternType))
        }

        return patterns
    }

    fun getPatternContent(pattern: Pattern) : String? {
        val request = Request.Builder()
            .url(ModelConfig.PATTERNS_RAW_BASE_URL + pattern.toString())
            .build()

        return client.newCall(request).execute().use { response ->
            if (response.isSuccessful) {
                response.body.string()
            } else {
                null
            }
        }
    }

    private fun parseFilenamesFromIndex(json: String): List<String> {
        // Parse simple JSON: [{"conwaylife":["a.rle","b.rle",...]}]
        val arrayStart = json.indexOf("[", json.indexOf("conwaylife"))
        val arrayEnd = json.lastIndexOf("]", json.lastIndexOf("]") - 1)
        if (arrayStart == -1 || arrayEnd == -1) return emptyList()

        val arrayContent = json.substring(arrayStart + 1, arrayEnd)
        return arrayContent.split(",")
            .map { it.trim().removeSurrounding("\"") }
            .filter { it.isNotBlank() }
    }
}