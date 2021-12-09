package data

data class BingoRow(val values: MutableMap<Int, Boolean>){
    fun mark(value: Int): Boolean {
        if(values.containsKey(value))
            values[value] = true

        return !values.containsValue(false)
    }

    fun getUnmarkedSum(): Int {
        var sum = 0
        for((key, value) in values){
            if(!value) sum += key
        }
        return sum
    }
}

data class BingoCard(val rows: List<BingoRow>, val cols: List<BingoRow>, val diags: List<BingoRow>){
    fun mark(value: Int): Boolean {
        var bingo = false
        for(row in rows){
            bingo =
                if(!bingo) row.mark(value)
                else true
        }
        for(col in cols){
            bingo =
                if(!bingo) col.mark(value)
                else true
        }
        //Diagonals don't count apparently!
//        for(diag in diags){
//            bingo =
//                if(!bingo) diag.mark(value)
//                else bingo
//        }
        return bingo
    }

    fun getUnmarkedSum(): Int {
        var sum = 0
        for(row in rows){
            sum += row.getUnmarkedSum()
        }
        return sum
    }
}

data class BingoGame(val cards: List<BingoCard>, val draw: List<Int>)