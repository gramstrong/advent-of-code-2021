package readers

import data.BingoCard
import data.BingoGame
import data.BingoRow
import java.io.File

class BingoReader: IReader<BingoGame> {

    private fun getBingoRow(values: List<Int>): BingoRow {
        val rowValues: MutableMap<Int, Boolean> = mutableMapOf()

        for(value in values)
            rowValues[value] = false

        return BingoRow(rowValues)
    }

    override fun read(fileName: String): List<BingoGame> {
        val lines = File(fileName).readLines().iterator()
        if(!lines.hasNext()) return listOf()

        val draw: List<Int> = lines.next().split(",").map { s -> s.toInt() }
        val wsReg = "\\s+".toRegex()
        val cards: MutableList<BingoCard> = ArrayList()



        while(lines.hasNext()) {
            val next = lines.next()

            if (next.isNotEmpty()) {

                val row1 = next.trim().split(wsReg).map { s -> s.toInt() }
                val row2 = lines.next().trim().split(wsReg).map { s -> s.toInt() }
                val row3 = lines.next().trim().split(wsReg).map { s -> s.toInt() }
                val row4 = lines.next().trim().split(wsReg).map { s -> s.toInt() }
                val row5 = lines.next().trim().split(wsReg).map { s -> s.toInt() }

                val col1 = listOf(row1[0], row2[0], row3[0], row4[0], row5[0])
                val col2 = listOf(row1[1], row2[1], row3[1], row4[1], row5[1])
                val col3 = listOf(row1[2], row2[2], row3[2], row4[2], row5[2])
                val col4 = listOf(row1[3], row2[3], row3[3], row4[3], row5[3])
                val col5 = listOf(row1[4], row2[4], row3[4], row4[4], row5[4])

                val diag1 = listOf(row1[0], row2[1], row3[2], row4[3], row5[4])
                val diag2 = listOf(row1[4], row2[3], row3[2], row4[1], row5[0])

                cards.add(BingoCard(listOf(
                    getBingoRow(row1),
                    getBingoRow(row2),
                    getBingoRow(row3),
                    getBingoRow(row4),
                    getBingoRow(row5)),
                    listOf(
                    getBingoRow(col1),
                    getBingoRow(col2),
                    getBingoRow(col3),
                    getBingoRow(col4),
                    getBingoRow(col5)),
                    listOf(
                    getBingoRow(diag1),
                    getBingoRow(diag2))
                ))
            }
        }

        return listOf(BingoGame(cards, draw))
    }
}