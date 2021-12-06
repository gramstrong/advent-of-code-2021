package readers

import data.Vent
import data.Vents
import java.io.File

class VentsReader: IReader<Vents> {

    override fun read(fileName: String): List<Vents> {
        val lines = File(fileName).readLines().iterator()
        val reg = Regex("([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)")
        var vents: MutableList<Vent> = ArrayList<Vent>()


        for(line in lines){
            val groupVal = reg.matchEntire(line)?.groupValues
            if(groupVal?.size == 5){
                vents.add(Vent(groupVal[1].toInt(), groupVal[2].toInt(), groupVal[3].toInt(), groupVal[4].toInt()))
            }
        }

        return listOf(Vents(vents))
    }
}