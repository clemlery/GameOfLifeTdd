package gameoflifetdd.model.dataccess

import gameoflifetdd.config.ModelConfig
import okhttp3.OkHttpClient
import okhttp3.Request

object Scraper {
    fun getAllPatternsName() : List<Pattern> {
        TODO()
    }

    fun getPatternContent(pattern: Pattern) : String? {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(ModelConfig.PATTERNS_BANK_URL + pattern.toString())
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
            .build()

        return client.newCall(request).execute().use { response ->
            if (response.isSuccessful) {
                response.body.string()
            } else {
                null
            }
        }
    }
}