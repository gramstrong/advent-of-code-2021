package data

import readers.LavaTubeReader


data class LavaTube(val height: Int, val u: Int, val d: Int, val l: Int, val r: Int, var visited: Boolean = false){

    private var uTube: LavaTube? = null
    private var dTube: LavaTube? = null
    private var lTube: LavaTube? = null
    private var rTube: LavaTube? = null

    fun setVisited(){
        visited = true
    }

    fun isVisited(): Boolean{
        return visited
    }

    fun isMin(): Boolean = (u < 0 || u > height) &&
            (d < 0 || d > height) &&
            (l < 0 || l > height) &&
            (r < 0 || r > height)

    fun setNeighbors(uNeighbor: LavaTube?, dNeighbor: LavaTube?, lNeighbor: LavaTube?, rNeighbor: LavaTube?){
        uTube = uNeighbor
        dTube = dNeighbor
        lTube = lNeighbor
        rTube = rNeighbor
    }

    fun getUp(): LavaTube? = uTube
    fun getDown(): LavaTube? = dTube
    fun getLeft(): LavaTube? = lTube
    fun getRight(): LavaTube? = rTube

}