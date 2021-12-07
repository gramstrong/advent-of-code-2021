package readers

import data.LanternFish
import java.io.File

class CSVIntReader: IReader<Int> {
    override fun read(fileName: String): List<Int> {
        var ints: MutableList<Int> = ArrayList<Int>()
        File(fileName).readLines().forEach { s -> s.split(",").forEach {n -> ints.add(n.toInt())}}
        return ints;
    }
}