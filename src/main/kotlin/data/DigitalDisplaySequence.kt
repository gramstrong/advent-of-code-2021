package data

/*
 This class and DigitalDisplay are pretty convoluted ¯\_(ツ)_/¯
 */

data class DigitalDisplaySequence(val digits: List<DigitalDisplay>, val sequence: List<DigitalDisplay>){

    private val EMPTY_DISPLAY: DigitalDisplay = DigitalDisplay("")

    private fun getZero(): DigitalDisplay{
        val six = getSix()
        val nine = getNine()

        for (digit in digits)
            if(digit.canZero() && digit != six && digit != nine) return digit
        return EMPTY_DISPLAY;
    }

    private fun getOne(): DigitalDisplay{
        for (digit in digits)
            if(digit.isOne()) return digit
        return EMPTY_DISPLAY;
    }

    private fun getTwo(): DigitalDisplay{
        val one = getOne()
        val five = getFive()
        val nine = getNine()
        val six = getSix()
        val zero = getZero()

        for(digit in digits)
            if(digit.canTwo() &&
                !digit.contains(one) &&
                digit != five &&
                digit != nine &&
                digit != six &&
                digit != zero) return digit
        return six
    }

    private fun getThree(): DigitalDisplay{
        val one = getOne()
        val five = getFive()
        val nine = getNine()
        val six = getSix()
        val zero = getZero()

        for(digit in digits)
            if(digit.canThree() &&
                digit.contains(one) &&
                digit != five &&
                digit != nine &&
                digit != six &&
                digit != zero) return digit
        return six
    }

    private fun getFour(): DigitalDisplay{
        for (digit in digits)
            if(digit.isFour()) return digit
        return EMPTY_DISPLAY;
    }

    private fun getFive(): DigitalDisplay{
        val six = getSix()
        for(digit in digits)
            if(digit.canFive() && digit.diff(six).length == 1) return digit
        return six
    }

    private fun getSix(): DigitalDisplay{
        val one = getOne()
        for(digit in digits)
            if(digit.canSix() && !digit.contains(one)) return digit
        return one
    }

    private fun getSeven(): DigitalDisplay{
        for (digit in digits)
            if(digit.isSeven()) return digit
        return EMPTY_DISPLAY;
    }
    private fun getEight(): DigitalDisplay{
        for (digit in digits)
            if(digit.isEight()) return digit
        return EMPTY_DISPLAY;
    }

    private fun getNine(): DigitalDisplay{
        val six = getSix()
        val four = getFour()
        for(digit in digits)
            if(digit.canNine() && digit.contains(four) && digit != six) return digit
        return six
    }


    fun getNumEasyDigits(): Int{
        var count = 0;
        for(digit in sequence){
            if(digit.equals(getOne())) count++
            if(digit.equals(getFour())) count++
            if(digit.equals(getSeven())) count++
            if(digit.equals(getEight())) count++
        }
        return count;
    }

    fun printSolved(){
        println("---------")
        println("0: ${getZero().part}")
        println("1: ${getOne().part}")
        println("2: ${getTwo().part}")
        println("3: ${getThree().part}")
        println("4: ${getFour().part}")
        println("5: ${getFive().part}")
        println("6: ${getSix().part}")
        println("7: ${getSeven().part}")
        println("8: ${getEight().part}")
        println("9: ${getNine().part}")
    }

    fun decode(): Int{
        var decodeMap: Map<String, Int> = mapOf(
            getZero().part to 0,
            getOne().part to 1,
            getTwo().part to 2,
            getThree().part to 3,
            getFour().part to 4,
            getFive().part to 5,
            getSix().part to 6,
            getSeven().part to 7,
            getEight().part to 8,
            getNine().part to 9,
        )

        return "${decodeMap.get(sequence[0].part)}${decodeMap.get(sequence[1].part)}${decodeMap.get(sequence[2].part)}${decodeMap.get(sequence[3].part)}".toInt()
    }
}