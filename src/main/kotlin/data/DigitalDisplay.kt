package data

data class DigitalDisplay(val part: String) {
    fun contains(digit: DigitalDisplay): Boolean = digit.part.fold(true) { acc, c -> part.contains(c) && acc }
    fun contains(part: String): Boolean = part.fold(true) { acc, c -> part.contains(c) && acc }

    fun diff(digit: DigitalDisplay): String = digit.part.split("").minus(part.split("").toSet()).joinToString("")

    fun canZero(): Boolean = part.length == 6
    fun isOne(): Boolean = part.length == 2
    fun canTwo(): Boolean = part.length == 5
    fun canThree(): Boolean = part.length == 5
    fun isFour(): Boolean = part.length == 4
    fun canFive(): Boolean = part.length == 5
    fun canSix(): Boolean = part.length == 6
    fun isSeven(): Boolean = part.length == 3
    fun isEight(): Boolean = part.length == 7
    fun canNine(): Boolean = part.length == 6
}