package problems.day4

import data.BingoGame
import problems.ISolver
import readers.IReader

class DayFour(private val reader: IReader<BingoGame>) : ISolver {

    override fun solvePart1(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day4/$file").iterator()

        if(input.hasNext())
            for(game in input){
                for(num in game.draw){
                    for(i in game.cards.indices){
                        val complete = game.cards[i].mark(num)
                        if(complete){
                            return "${game.cards[i].getUnmarkedSum() * num}"
                        }
                    }
                }
            }

        return ""
    }

    private fun recurseUnwinningCards(game: BingoGame): String{

        for(i in game.draw.indices){
            for(j in game.cards.indices){
                val complete = game.cards[j].mark(game.draw[i])
                if(complete){
                    return if(game.cards.size == 1) "${game.cards[0].getUnmarkedSum() * game.draw[i]}"
                    else recurseUnwinningCards(BingoGame(game.cards.minus(game.cards[j]), game.draw.slice(i until game.draw.size)))
                }
            }
        }
        return ""
    }

    override fun solvePart2(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day4/$file").iterator()

        if(input.hasNext())
            for(game in input){
                return recurseUnwinningCards(game)
            }

        return ""
    }
}