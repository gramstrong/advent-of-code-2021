package problems.day10
import problems.ISolver
import readers.IReader
import java.util.*
import kotlin.collections.ArrayList

class DayTen(private val reader: IReader<String>) : ISolver {

    private val open = listOf<String>("(", "[", "{", "<")
    private val closed = listOf<String>("(", "]", "}", ">")

    private fun getCharScore(char: String): Int
        = when (char) {
            ")" -> 3
            "]" -> 57
            "}" -> 1197
            ">" -> 25137
            else -> 0
        }

    private fun getCharErrorScoreMap(): MutableMap<String,Int> = mutableMapOf(
        ")" to 0,
        "]" to 0,
        "}" to 0,
        ">" to 0
    )

    private fun getCharCompleteScoreMap(): MutableMap<String,Int> = mutableMapOf(
        ")" to 1,
        "]" to 2,
        "}" to 3,
        ">" to 4
    )

    private fun getClosedBracketMap(): Map<String, String> = mapOf(
        ")" to "(",
        "]" to "[",
        "}" to "{",
        ">" to "<",
    )
    private fun getOpenBracketMap(): Map<String, String> = mapOf(
        "(" to ")",
        "[" to "]",
        "{" to "}",
        "<" to ">",
    )

    private fun isOpen(char: String) = open.contains(char)

    private fun scoreSyntaxError(line: String): Int {
        val bracketsStack: ArrayDeque<String> = ArrayDeque<String>()
        var illegalChars: MutableMap<String, Int> = getCharErrorScoreMap()

        for (char in line.chunked(1)){
            if(isOpen(char)){
                bracketsStack.push(char)
            }
            else if(bracketsStack.pop() != getClosedBracketMap().get(char)){
                illegalChars[char] = illegalChars.getOrDefault(char, 0) + 1
            }
        }
        var score = 0
        illegalChars.forEach{ k, v -> score += getCharScore(k) * v }
        return score
    }

    private fun scoreAutoComplete(line: String): Long {
        val bracketsStack: ArrayDeque<String> = ArrayDeque<String>()

        for (char in line.chunked(1)){
            if(isOpen(char)) bracketsStack.push(char)
            else bracketsStack.pop()
        }

        var score: Long = 0
        while(!bracketsStack.isEmpty()){
            score *= 5
            score += getCharCompleteScoreMap().getOrDefault(getOpenBracketMap()[bracketsStack.pop()], 0)
        }

        return score
    }

    override fun solvePart1(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day10/$file")
        var score = 0

        for(line in input)
            score += scoreSyntaxError(line)

        return "$score"
    }

    override fun solvePart2(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day10/$file")
        val incompleteLines = input.filter{ l -> scoreSyntaxError(l) == 0}
        var scores: MutableList<Long> = ArrayList()

        for(line in incompleteLines)
            scores.add(scoreAutoComplete(line))

        return "${scores.sorted()[scores.size/2]}"
    }
}