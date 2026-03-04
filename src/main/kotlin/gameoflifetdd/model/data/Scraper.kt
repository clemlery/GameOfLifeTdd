package gameoflifetdd.model.data

import gameoflifetdd.config.ModelConfig
import okhttp3.OkHttpClient
import okhttp3.Request

object Scraper {
    private val client = OkHttpClient()

    /**
     * Fetches the raw content of a pattern file from the remote pattern bank.
     *
     * @param pattern the pattern to download
     * @return the file content as a string, or null if the request failed
     */
    fun getPatternContent(pattern: Pattern) : String? {
        val request = Request.Builder()
            .url(ModelConfig.PATTERNS_BANK_URL + pattern.toString())
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