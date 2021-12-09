package problems.day2

import data.Move
import problems.ISolver
import readers.IReader

class DayTwo(private val reader: IReader<Move>) : ISolver {

    private var x = 0
    private var y = 0
    private var aim = 0

    private fun reset(){
        x = 0
        y = 0
        aim = 0
    }

    private fun makeMove(move: Move) {
        when (move.direction) {
            "forward" -> x += move.units
            "down" -> y += move.units
            "up" -> y -= move.units
        }
    }

    private fun makeAim(move: Move) {
        when (move.direction) {
            "forward" -> {
                makeMove(move)
                makeMove(Move("down", move.units * aim))
            }
            "down" -> aim += move.units
            "up" -> aim -= move.units
        }
    }

    override fun solvePart1(file: String): String {
        reset()
        val input: Iterator<Move> = reader.read("src/main/kotlin/problems/day2/$file").iterator()

        for(move: Move in input)
            makeMove(move)

        return "${x*y}"
    }

    override fun solvePart2(file: String): String {
        reset()
        val input: Iterator<Move> = reader.read("src/main/kotlin/problems/day2/$file").iterator()

        for(move: Move in input)
            makeAim(move)

        return "${x*y}"
    }}