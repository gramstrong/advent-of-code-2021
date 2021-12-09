package readers

import data.Move
import java.io.File

class MoveReader: IReader<Move> {
    override fun read(fileName: String): List<Move> {
        val lines: List<String> = File(fileName).readLines()

        val moves: List<Move> = lines.map { s ->
            val moveParams: List<String> = s.split(" ")
            Move(moveParams[0], moveParams[1].toInt())
        }

        return moves
    }
}