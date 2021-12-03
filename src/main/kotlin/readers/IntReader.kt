package readers

import java.io.File

class IntReader: IReader<Int> {
    override fun read(fileName: String): List<Int>
        = File(fileName).readLines().map { s -> s.toInt() }
}