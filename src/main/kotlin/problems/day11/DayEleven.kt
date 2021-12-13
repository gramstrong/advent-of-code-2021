package problems.day11
import data.Octopus
import problems.ISolver
import readers.IReader

class DayEleven(private val reader: IReader<Octopus>) : ISolver {

    fun printOctopi(octopi: List<Octopus>){
        var i = 0
        for(octopus in octopi){
            print(octopus.energy)
            i++
            if(i == 10){
                print("\n")
                i = 0
            }
        }
        print("\n")
    }

    fun energize(octopi: List<Octopus>): Int {
        var flashes = 0
        for(octopus in octopi)
            flashes += octopus.energize()

        reset(octopi)
        return flashes
    }

    fun reset(octopi: List<Octopus>){
        for(octopus in octopi) octopus.reset()
    }

    override fun solvePart1(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day11/$file")
        var flashes = 0

        for(i in 0 until 100){
            flashes += energize(input)
            //printOctopi(input)
        }

        return "$flashes"
    }

    override fun solvePart2(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day11/$file")
        var fullFlash = 1

        while(energize(input) != 100){
            fullFlash++
            //printOctopi(input)
        }

        return "$fullFlash"
    }
}