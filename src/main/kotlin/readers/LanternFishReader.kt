package readers

import data.LanternFish
import java.io.File

class LanternFishReader: IReader<LanternFish> {
    override fun read(fileName: String): List<LanternFish> {
        val fish: MutableList<LanternFish> = ArrayList()
        File(fileName).readLines().forEach { s -> s.split(",").forEach {n -> fish.add(LanternFish(n.toInt()))}}
        return fish
    }
}