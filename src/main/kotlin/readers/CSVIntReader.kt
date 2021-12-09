package readers

import java.io.File

class CSVIntReader: IReader<Int> {
    override fun read(fileName: String): List<Int> {
        val ints: MutableList<Int> = ArrayList()
        File(fileName).readLines().forEach { s -> s.split(",").forEach {n -> ints.add(n.toInt())}}
        return ints
    }
}