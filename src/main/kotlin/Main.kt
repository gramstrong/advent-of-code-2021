import problems.ISolver
import problems.day1.DayOne;
import readers.IntReader

fun solveDay(day: ISolver<Int>, dayNum: String){
    println("DAY ${dayNum}")
    println("========================================")
    println("Part 1")
    val dayOneSmall: String = DayOne().solvePart1(IntReader(), "small-input.txt")
    println("Small Input: $dayOneSmall")

    val dayOneLarge: String = DayOne().solvePart2(IntReader(), "large-input.txt")
    println("Large Input: $dayOneLarge")

    println("\n")
    println("Part 2")

    val dayOnePartTwoSmall: String = DayOne().solvePart2(IntReader(), "small-input.txt")
    println("Small Input: $dayOnePartTwoSmall")

    val dayOnePartTwoLarge: String = DayOne().solvePart2(IntReader(), "large-input.txt")
    println("Large Input: $dayOnePartTwoLarge")
    println("\n")
}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    solveDay(DayOne(), "01");
}