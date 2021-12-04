package readers

import data.Move
import java.io.File

class MoveReader: IReader<Move> {
    override fun read(fileName: String): List<Move> {
        var lines: List<String> = File(fileName).readLines()

        var moves: List<Move> = lines.map { s ->
            var moveParams: List<String> = s.split(" ")
            Move(moveParams.get(0), moveParams.get(1).toInt())
        }

        return moves
    }
}