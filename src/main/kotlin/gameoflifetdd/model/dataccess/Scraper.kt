package gameoflifetdd.model.dataccess

import gameoflifetdd.config.ModelConfig
import okhttp3.OkHttpClient
import okhttp3.Request

object Scraper {
    private val client = OkHttpClient()

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
}