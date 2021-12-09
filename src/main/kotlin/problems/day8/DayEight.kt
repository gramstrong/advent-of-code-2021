package problems.day8
import data.DigitalDisplay
import data.DigitalDisplaySequence
import problems.ISolver
import readers.DigitalReader
import readers.IReader

class DayEight(reader: IReader<DigitalDisplaySequence>) : ISolver {

    private val READER = reader

    override fun solvePart1(file: String): String {
        val input = READER.read("src/main/kotlin/problems/day8/$file")
        var count = 0

        for(sequence in input)
            count += sequence.getNumEasyDigits()

        return "${count}"
    }

    override fun solvePart2(file: String): String {
        val input = READER.read("src/main/kotlin/problems/day8/$file")
        var sum = 0;

        for(sequence in input){
            sum += sequence.decode()
        }


        return "$sum"
    }
}