package readers

import data.Octopus
import java.io.File

class OctopusReader: IReader<Octopus> {

    override fun read(fileName: String): List<Octopus> {
        val octopi: MutableList<MutableList<Octopus>> = ArrayList()
        var lines: MutableList<String> = ArrayList()
        File(fileName).readLines().forEach { s -> lines.add(s)}

        if(lines.size > 0){
            var intGrid: Array<IntArray> = Array(lines.size) {IntArray(lines[0].length)}
            for(i in intGrid.indices){
                intGrid[i] = lines[i].chunked(1).map{s -> s.toInt()}.toIntArray()
            }

            for(i in intGrid.indices){
                var row: MutableList<Octopus> = ArrayList()
                for(j in intGrid[i].indices){
                    val energy = intGrid[i][j]
                    row.add(Octopus(energy, false))
                }
                octopi.add(row)
            }

            for(i in octopi.indices){
                for(j in octopi[i].indices){
                    val ul = if(i > 0 && j > 0) octopi[i-1][j-1] else null
                    val u = if(i > 0) octopi[i-1][j] else null
                    val ur = if(i > 0 && j < octopi[0].indices.last) octopi[i-1][j+1] else null
                    val l = if(j > 0) octopi[i][j-1] else null
                    val r = if(j < octopi[0].indices.last) octopi[i][j+1] else null
                    val dl = if(i < octopi.indices.last && j > 0) octopi[i+1][j-1] else null
                    val d = if(i < octopi.indices.last) octopi[i+1][j] else null
                    val dr = if(i < octopi.indices.last && j < octopi[0].indices.last) octopi[i+1][j+1] else null

                    octopi[i][j].setNeighbors(ul, u, ur, l, r, dl, d, dr)
                }
            }
        }

        return octopi.flatten()
    }
}