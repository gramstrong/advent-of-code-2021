package readers

import java.io.File

class StringReader: IReader<String> {
    override fun read(fileName: String): List<String>
            = File(fileName).readLines()
}