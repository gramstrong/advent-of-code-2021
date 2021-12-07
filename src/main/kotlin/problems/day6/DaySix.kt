package problems.day6
import data.LanternFish
import problems.ISolver
import readers.IReader
import readers.LanternFishReader

class DaySix(reader: IReader<LanternFish>) : ISolver {

    private val READER = reader

    private fun spawn(fishes: MutableList<LanternFish>, days: Int): MutableList<LanternFish> {
        for(i in 1 .. days){
            var babies: MutableList<LanternFish> = ArrayList<LanternFish>();
            for(fish in fishes){
                if(fish.live()){
                    babies.add(LanternFish(8))
                }
            }
            fishes.addAll(babies)
        }

        return fishes
    }

    private fun spawnCount(fishes: MutableList<LanternFish>, days: Int): Long{
        var counts: MutableList<Long> = mutableListOf(0,0,0,0,0,0,0,0,0)
        for(fish in fishes)
            counts.set(fish.timer, counts.get(fish.timer) + 1)

        for(i in 0 until days){
            counts.add(counts.removeFirst())
            counts[6] += counts[8]
        }
        return counts.reduce { acc, n -> acc + n }
    }

    override fun solvePart1(file: String): String {
        val input = READER.read("src/main/kotlin/problems/day6/$file")
        var fishes: MutableList<LanternFish> = input.toMutableList()


        return "${spawn(fishes, 80).size}"
    }

    override fun solvePart2(file: String): String {
        val input = READER.read("src/main/kotlin/problems/day6/$file")

        return "${spawnCount(input.toMutableList(), 256)}"
    }
}