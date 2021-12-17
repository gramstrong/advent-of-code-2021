package problems.day13
import data.CameraManual
import problems.ISolver
import readers.CameraManualReader
import readers.IReader

class DayThirteen(private val reader: IReader<CameraManual>) : ISolver {

    fun printManual(manual: CameraManual){
        var bounds = manual.getBounds()
        for(i in 0 until bounds.y){
            for(j in 0 until bounds.x){
                if(manual.isMarked(j, i)) print("#")
                else print(".")
            }
            print("\n")
        }
        print("\n")
    }

    override fun solvePart1(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day13/$file").iterator()
        if(input.hasNext()){
            val manual = input.next()
            manual.fold()
            return "${manual.points.size}"
        }
        return ""
    }

    override fun solvePart2(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day13/$file").iterator()
        if(input.hasNext()){
            val manual = input.next()
            while(!manual.doneFolding()){
                manual.fold()
            }
            printManual(manual)
            return "${manual.points.size}"
        }
        return ""
    }
}