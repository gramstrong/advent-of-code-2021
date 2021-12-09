package readers

import data.DigitalDisplay
import data.DigitalDisplaySequence
import java.io.File

class DigitalReader: IReader<DigitalDisplaySequence> {

    private fun getDigitsList(s: String): List<DigitalDisplay>{
        var lineDigits: MutableList<DigitalDisplay> = ArrayList()
        s.split(" ").forEach { d ->
            if(d == "|") return@forEach
            lineDigits.add(DigitalDisplay(d.toCharArray().sorted().joinToString("")))
        }
        return lineDigits
    }

    override fun read(fileName: String): List<DigitalDisplaySequence> {
        val displaySequences: MutableList<DigitalDisplaySequence> = ArrayList<DigitalDisplaySequence>()
        val reader = File(fileName).bufferedReader().lineSequence().iterator()

        while(reader.hasNext()){
            var digits: MutableList<DigitalDisplay> = ArrayList<DigitalDisplay>(10)
            var sequence: MutableList<DigitalDisplay> = ArrayList<DigitalDisplay>(4)
            var line = reader.next()


            val digitString = line.split("|").get(0).trim()
            val sequenceString = line.split("|").get(1).trim()

            digits.addAll(getDigitsList(digitString))
            sequence.addAll(getDigitsList(sequenceString))
            displaySequences.add(DigitalDisplaySequence(digits, sequence))
        }
        return displaySequences;
    }
}