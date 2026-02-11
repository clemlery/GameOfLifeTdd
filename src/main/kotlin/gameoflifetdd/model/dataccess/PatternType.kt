package gameoflifetdd.model.dataccess

enum class PatternType(val filenameExtension: String) {
    RLE(".rle"),
    CELLS(".cells"),
    MC(".mc");

    override fun toString(): String = filenameExtension
}