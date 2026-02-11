package gameoflifetdd.model.dataccess

fun main() {
    val scraper = Scraper()
    println(scraper.getPatternContent(Pattern("", PatternType.CELLS)))
}