package problems.day3

import data.Bits
import data.GammaEpsilon
import problems.ISolver
import readers.IReader

class DayThree(private val reader: IReader<Bits>) : ISolver {

    private fun getGammaEpsilon(input: List<Bits>): GammaEpsilon {
        val length = input.size
        val bitLength = if(length > 0) input[0].getSize()
        else 0

        val sum = Array(bitLength) { 0 }
        val gamma = Array(bitLength) { 0 }
        val epsilon = Array(bitLength) { 0 }

        for(i in 0 until bitLength)
        {
            for(bits in input)
                sum[i] += bits.get(i)
        }

        for(i in 0 until bitLength){
            if(sum[i] >= (length/2 + length%2)){
                gamma[i] = 1
                epsilon[i] = 0
            }
            else{
                gamma[i] = 0
                epsilon[i] = 1
            }
        }
        return GammaEpsilon(Bits(gamma), Bits(epsilon))
    }

    private fun filter(bitsList: MutableList<Bits>, bitFilter: Bits, filterIndex: Int): MutableList<Bits>{

        val removed: MutableList<Bits> = ArrayList()

        for(i in 0 until bitsList.size){
            if(removed.size == bitsList.size-1) break
            val filterBit = bitFilter.get(filterIndex)
            val bit = bitsList[i].get(filterIndex)
            if(bit != filterBit)
                removed.add(bitsList[i])
        }

        bitsList.removeAll(removed)
        return bitsList
    }

    private fun gammaFilter(bitsList: MutableList<Bits>, bitFilter: Bits, filterIndex: Int): Bits{
        if((filterIndex > bitFilter.getSize()-1) ||
            (bitsList.size == 1)) return bitsList.iterator().next()

        val newGammaEpsilon = getGammaEpsilon(filter(bitsList, bitFilter, filterIndex))
        return gammaFilter(bitsList, newGammaEpsilon.gamma, filterIndex+1)
    }

    private fun epsilonFilter(bitsList: MutableList<Bits>, bitFilter: Bits, filterIndex: Int): Bits{
        if((filterIndex > bitFilter.getSize()-1) ||
            (bitsList.size == 1)) return bitsList.iterator().next()

        val newGammaEpsilon = getGammaEpsilon(filter(bitsList, bitFilter, filterIndex))
        return epsilonFilter(bitsList, newGammaEpsilon.epsilon, filterIndex+1)
    }


    override fun solvePart1(file: String): String {
        val input: List<Bits> = reader.read("src/main/kotlin/problems/day3/$file")
        val gammaEpsilon = getGammaEpsilon(input)

        val result = gammaEpsilon.gamma.toDecimal()*
                gammaEpsilon.epsilon.toDecimal()

        return "$result"
    }

    override fun solvePart2(file: String): String {
        val input: List<Bits> = reader.read("src/main/kotlin/problems/day3/$file")
        val oxygen: MutableList<Bits> = ArrayList()
        val scrubber: MutableList<Bits> = ArrayList()
        val gammaEpsilon = getGammaEpsilon(input)

        oxygen.addAll(input)
        scrubber.addAll(input)

        val oxygenBits: Bits = gammaFilter(oxygen, gammaEpsilon.gamma, 0)
        val scrubberBits: Bits = epsilonFilter(scrubber, gammaEpsilon.epsilon, 0)
        val lifeSupportRating = oxygenBits.toDecimal() * scrubberBits.toDecimal()

        return "$lifeSupportRating"
    }
}