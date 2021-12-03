package problems

import readers.IReader

interface ISolver<in T> {
    fun solvePart1(reader: IReader<T>, file: String): String
    fun solvePart2(reader: IReader<T>, file: String): String
}