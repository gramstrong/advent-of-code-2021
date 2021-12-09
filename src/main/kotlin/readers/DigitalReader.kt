package readers

import data.DigitalDisplay
import data.DigitalDisplaySequence
import java.io.File

class DigitalReader: IReader<DigitalDisplaySequence> {

    private fun getDigitsList(s: String): List<DigitalDisplay>{
        val lineDigits: MutableList<DigitalDisplay> = ArrayList()
        s.split(" ").forEach { d ->
            if(d == "|") return@forEach
            lineDigits.add(DigitalDisplay(d.toCharArray().sorted().joinToString("")))
        }
        return lineDigits
    }

    override fun read(fileName: String): List<DigitalDisplaySequence> {
        val displaySequences: MutableList<DigitalDisplaySequence> = ArrayList()
        val reader = File(fileName).bufferedReader().lineSequence().iterator()

        while(reader.hasNext()){
            val digits: MutableList<DigitalDisplay> = ArrayList(10)
            val sequence: MutableList<DigitalDisplay> = ArrayList(4)
            val line = reader.next()


            val digitString = line.split("|")[0].trim()
            val sequenceString = line.split("|")[1].trim()

            digits.addAll(getDigitsList(digitString))
            sequence.addAll(getDigitsList(sequenceString))
            displaySequences.add(DigitalDisplaySequence(digits, sequence))
        }
        return displaySequences
    }
}