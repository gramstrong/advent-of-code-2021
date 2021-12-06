package data

data class Vent(val x1: Int, val y1: Int, val x2: Int, val y2: Int){
    fun isDiag(): Boolean {
        return x1 != x2 && y1 != y2
    }
    fun isHorizontal(): Boolean{
        return y1 == y2 && x1 != x2
    }
    fun isVertical(): Boolean{
        return x1 == x2 && y1 != y2
    }
}

data class Vents (val ventList: List<Vent>){
    fun getOrthogonal(): List<Vent>{
        return ventList.filter { v -> !v.isDiag() }
    }
    fun getVertical(): List<Vent>{
        return ventList.filter { v -> v.isVertical() }
    }
    fun getHorizontal(): List<Vent>{
        return ventList.filter { v -> v.isHorizontal() }
    }
}