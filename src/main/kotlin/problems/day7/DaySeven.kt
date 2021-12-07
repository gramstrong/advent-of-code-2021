package problems.day7
import problems.ISolver
import readers.IReader
import kotlin.math.abs

class DaySeven(reader: IReader<Int>) : ISolver {

    private val READER = reader

    private fun testSimpleFuelBurn(curr: Int, cost: Int, crabs: List<Int>): Int{
        val higher = crabs.fold(0){ acc, n -> acc + abs((curr+1)-n) }
        if(higher < cost)
            return testSimpleFuelBurn(curr+1, higher, crabs)

        val lower = crabs.fold(0){ acc, n -> acc + abs((curr-1)-n) }
        if(lower < cost)
            return testSimpleFuelBurn(curr-1, lower, crabs)

        return cost;
    }

    private fun testGraduatedFuelBurn(curr: Int, cost: Int, crabs: List<Int>): Int{
        val higher = crabs.fold(0){ acc, n -> acc + IntRange(0, abs((curr+1)-n)).sum() }
        if(higher < cost)
            return testGraduatedFuelBurn(curr+1, higher, crabs)

        val lower = crabs.fold(0){ acc, n -> acc + IntRange(0, abs((curr-1)-n)).sum() }
        if(lower < cost)
            return testGraduatedFuelBurn(curr-1, lower, crabs)

        return cost;
    }

    override fun solvePart1(file: String): String {
        val input = READER.read("src/main/kotlin/problems/day7/$file")
        val avg = input.sum()/input.size
        val avgCost = input.fold(0) { acc, n -> acc + abs(avg-n) }

        return "${testSimpleFuelBurn(avg, avgCost, input)}"
    }

    override fun solvePart2(file: String): String {
        val input = READER.read("src/main/kotlin/problems/day7/$file")
        val avg = input.sum()/input.size
        val avgCost = input.fold(0) { acc, n -> acc + IntRange(0, abs(avg-n)).sum() }

        return "${testGraduatedFuelBurn(avg, avgCost, input)}"
    }
}