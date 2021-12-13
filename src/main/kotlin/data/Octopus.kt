package data

data class Octopus(var energy: Int, var flashed: Boolean = false){

    private var neighbors: MutableList<Octopus?> = ArrayList()

    fun reset(){
        if(energy > 9) energy = 0
        flashed = false
    }

    fun energize(): Int{
        var flash = 0
        energy += 1

        if(energy > 9 && !flashed){
            flashed = true
            flash += 1
            for(octopus in neighbors)
                flash += octopus?.energize() ?: 0
        }

        return flash
    }

    fun setNeighbors(
        ul: Octopus?,
        u: Octopus?,
        ur: Octopus?,
        l: Octopus?,
        r: Octopus?,
        dl: Octopus?,
        d: Octopus?,
        dr: Octopus?,
    ){
        neighbors = mutableListOf( ul, u, ur, l, r, dl, d, dr)
    }

}