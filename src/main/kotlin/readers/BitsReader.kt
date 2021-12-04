package readers

import data.Bits
import java.io.File

class BitsReader: IReader<Bits> {
    override fun read(fileName: String): List<Bits>
            = File(fileName).readLines().map { s -> Bits(s) }
}