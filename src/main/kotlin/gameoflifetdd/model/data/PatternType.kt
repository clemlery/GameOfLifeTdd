package gameoflifetdd.model.data

enum class PatternType(val filenameExtension: String) {
    RLE("rle"),
    CELLS("cells"),
    MC("mc");


    override fun toString(): String = filenameExtension
}