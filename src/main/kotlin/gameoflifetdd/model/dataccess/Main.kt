package gameoflifetdd.model.dataccess

fun main() {
    val patterns = Scraper.getAllPatterns(10)

    patterns.forEachIndexed { i, pattern ->
        println("$i: $pattern")
    }

    if (patterns.isNotEmpty()) {
        println("\n--- Content of ${patterns[0]} ---")
        println(Scraper.getPatternContent(patterns[0]))
    }
}