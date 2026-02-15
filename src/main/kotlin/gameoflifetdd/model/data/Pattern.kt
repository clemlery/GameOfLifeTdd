package gameoflifetdd.model.data

data class Pattern(
    val name: String,
    val type: PatternType
) {
    override fun toString(): String {
        return "$name.$type"
    }
}
