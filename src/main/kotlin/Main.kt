import problems.ISolver
import problems.day1.DayOne;
import problems.day2.DayTwo
import problems.day3.DayThree
import readers.BitsReader
import readers.IntReader
import readers.MoveReader

fun solveDay(day: ISolver, dayNum: String){
    println("DAY ${dayNum}")
    println("========================================")
    println("Part 1")
    val dayOneSmall: String = day.solvePart1("small-input.txt")
    println("Small Input: $dayOneSmall")

    val dayOneLarge: String = day.solvePart1("large-input.txt")
    println("Large Input: $dayOneLarge")

    println("\n")
    println("Part 2")

    val dayOnePartTwoSmall: String = day.solvePart2( "small-input.txt")
    println("Small Input: $dayOnePartTwoSmall")

    val dayOnePartTwoLarge: String = day.solvePart2("large-input.txt")
    println("Large Input: $dayOnePartTwoLarge")
    println("\n")
}

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    solveDay(DayOne(IntReader()), "01");
    solveDay(DayTwo(MoveReader()), "02")
    solveDay(DayThree(BitsReader()), "03")
}