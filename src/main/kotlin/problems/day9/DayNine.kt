package problems.day9
import data.LavaTube
import problems.ISolver
import readers.IReader

class DayNine(private val reader: IReader<LavaTube>) : ISolver {

    private fun getBasin(tube: LavaTube?, basin: MutableSet<LavaTube>): Set<LavaTube> {
        if(tube == null || tube.height == 9 || tube.isVisited()) return emptySet()
        basin.add(tube)
        tube.setVisited()

        getBasin(tube.getUp(), basin)

        getBasin(tube.getLeft(), basin)

        getBasin(tube.getRight(), basin)

        getBasin(tube.getDown(), basin)

        return basin
    }

    override fun solvePart1(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day9/$file")
        var risk = 0

        for(tube in input)
            if(tube.isMin()) {
                risk += 1+tube.height
            }

        return "${risk}"
    }

    override fun solvePart2(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day9/$file")
        var basins: MutableSet<Set<LavaTube>> = mutableSetOf()


        for(i in input.indices)
            basins.add(getBasin(input[i], mutableSetOf()))

        val iter = basins.sortedBy { b -> -b.size }.iterator()

        return "${iter.next().size * iter.next().size * iter.next().size}"
    }
}