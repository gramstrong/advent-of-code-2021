package problems.day2

import problems.ISolver
import readers.IReader
import java.io.File

data class Move(val direction: String, val units: Int)

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

class DayTwo(reader: IReader<Move>) : ISolver {

    private val READER = reader
    private var x = 0
    private var y = 0
    private var aim = 0;

    fun reset(){
        x = 0;
        y = 0;
        aim = 0;
    }

    fun makeMove(move: Move) {
        if(move.direction == "forward")
            x += move.units
        else if(move.direction == "down")
            y += move.units
        else if(move.direction == "up")
            y -= move.units
    }

    fun makeAim(move: Move) {
        if(move.direction == "forward"){
            makeMove(move)
            makeMove(Move("down", move.units * aim))
        }
        else if(move.direction == "down")
            aim += move.units
        else if(move.direction == "up")
            aim -= move.units
    }

    override fun solvePart1(file: String): String {
        reset()
        val input: Iterator<Move> = READER.read("src/main/kotlin/problems/day2/$file").iterator()

        for(move: Move in input)
            makeMove(move);

        return "${x*y}"
    }

    override fun solvePart2(file: String): String {
        reset()
        val input: Iterator<Move> = READER.read("src/main/kotlin/problems/day2/$file").iterator()

        for(move: Move in input)
            makeAim(move);

        return "${x*y}"
    }}