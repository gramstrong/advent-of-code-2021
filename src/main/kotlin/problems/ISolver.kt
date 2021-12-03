package problems

import readers.IReader

interface ISolver {
    fun solvePart1(file: String): String
    fun solvePart2(file: String): String
}