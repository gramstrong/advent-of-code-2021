package problems.day1

import problems.ISolver
import readers.IReader

class DayOne(private val reader: IReader<Int>) : ISolver {

    override fun solvePart1(file: String): String {
        val input: Iterator<Int> = reader.read("src/main/kotlin/problems/day1/$file").iterator()
        var prev: Int
        var curr: Int
        var increased = 0

        curr = input.next()

        while(input.hasNext()){
            prev = curr

            if(input.hasNext())
            {
                curr = input.next()
                if(curr > prev) increased++
            }

        }
        return increased.toString()
    }

    override fun solvePart2(file: String): String {
        val input: Array<Int> = reader.read("src/main/kotlin/problems/day1/$file").toTypedArray()
        var prev: Int
        var curr = 0
        var increased = 0

        for(i in input.indices) {
            prev = curr

            val val1 = input[i]
            var val2 = 0
            var val3 = 0
            if(i < input.size-2) val2 = input[i+1]
            if(i < input.size-3) val3 = input[i+2]

            curr = val1 + val2 + val3
            if(curr > prev && i!=0) increased++
        }
        return increased.toString()
    }}