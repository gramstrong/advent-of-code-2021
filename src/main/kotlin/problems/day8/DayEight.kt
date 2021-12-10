package problems.day8
import data.DigitalDisplaySequence
import problems.ISolver
import readers.IReader

class DayEight(private val reader: IReader<DigitalDisplaySequence>) : ISolver {

    override fun solvePart1(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day8/$file")
        var count = 0

        for(sequence in input)
            count += sequence.getNumEasyDigits()

        return "${count}"
    }

    override fun solvePart2(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day8/$file")
        var sum = 0;

        for(sequence in input){
            sum += sequence.decode()
        }


        return "$sum"
    }
}