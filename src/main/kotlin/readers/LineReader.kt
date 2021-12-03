package readers

import java.io.File

class LineReader: IReader<String> {
    override fun read(fileName: String): List<String>
            = File(fileName).readLines().toList()
}