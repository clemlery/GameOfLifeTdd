package gameoflifetdd.model.data

enum class PatternType(val filenameExtension: String) {
    RLE("rle"),
    CELLS("cells"),
    MC("mc");


    /**
     * Returns the file extension string associated with this pattern type.
     */
    override fun toString(): String = filenameExtension
}