package readers

import data.LavaTube
import java.io.File

class LavaTubeReader: IReader<LavaTube> {

    private val def: LavaTube = LavaTube(-1,-1,-1,-1,-1)

    override fun read(fileName: String): List<LavaTube> {
        val tubes: MutableList<MutableList<LavaTube>> = ArrayList()
        var lines: MutableList<String> = ArrayList()
        File(fileName).readLines().forEach { s -> lines.add(s)}

        if(lines.size > 0){
            var intGrid: Array<IntArray> = Array(lines.size) {IntArray(lines[0].length)}
            for(i in intGrid.indices){
                intGrid[i] = lines[i].chunked(1).map{s -> s.toInt()}.toIntArray()
            }

            for(i in intGrid.indices){
                var row: MutableList<LavaTube> = ArrayList()
                for(j in intGrid[i].indices){
                    val height = intGrid[i][j]
                    val u = if(i > 0) intGrid[i-1][j] else -1
                    val d = if(i < intGrid.indices.last) intGrid[i+1][j] else -1
                    val l = if(j > 0) intGrid[i][j-1] else -1
                    val r = if(j < intGrid[0].indices.last) intGrid[i][j+1] else -1

                    row.add(LavaTube(height, u, d, l, r))
                }
                tubes.add(row)
            }

            for(i in tubes.indices){
                for(j in tubes[i].indices){
                    val u = if(i > 0) tubes[i-1][j] else null
                    val d = if(i < tubes.indices.last) tubes[i+1][j] else null
                    val l = if(j > 0) tubes[i][j-1] else null
                    val r = if(j < tubes[0].indices.last) tubes[i][j+1] else null

                    tubes[i][j].setNeighbors(u,d,l,r)
                }
            }
        }

        return tubes.flatten()
    }
}