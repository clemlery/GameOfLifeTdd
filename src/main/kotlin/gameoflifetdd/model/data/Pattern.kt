package gameoflifetdd.model.data

data class Pattern(
    val name: String,
    val type: PatternType
) {
    /**
     * Returns the pattern identifier as "name.type" (e.g. "glider.rle").
     */
    override fun toString(): String {
        return "$name.$type"
    }
}
