package problems.day5
import data.Vent
import data.Vents
import problems.ISolver
import readers.IReader
import java.awt.Point

class DayFive(private val reader: IReader<Vents>) : ISolver {

    private fun getRange(a1: Int, a2: Int): IntRange {
        return if(a2 > a1) a1 .. a2 else a2 .. a1
    }

    private fun updateLineX(points: MutableMap<Point, Int>, x1: Int, x2: Int, y: Int){

        for (i in getRange(x1, x2)) {
            val count = points[Point(i, y)]
            points[Point(i, y)] = if(count != null) count + 1 else 1
        }
    }

    private fun updateLineY(points: MutableMap<Point, Int>, y1: Int, y2: Int, x: Int){

        for (i in getRange(y1, y2)) {
            val count = points[Point(x, i)]
            points[Point(x, i)] = if(count != null) count + 1 else 1
        }
    }

    private fun updateDiagLine(points: MutableMap<Point, Int>, x: Int, y: Int, m: Int, length: Int){
        var currx = x
        var curry = y

        for(i in 0 .. length){
            val count = points[Point(currx, curry)]
            points[Point(currx, curry)] = if(count != null) count + 1 else 1
            currx += 1
            curry += m
        }
    }

    override fun solvePart1(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day5/$file").iterator()
        if(!input.hasNext()) return ""

        val points: MutableMap<Point, Int> = mutableMapOf()
        val vents: List<Vent> = input.next().getOrthogonal()

        for (vent in vents){
            if(vent.x1 == vent.x2) {
                updateLineY(points, vent.y1, vent.y2, vent.x1)
            }
            if(vent.y1 == vent.y2){
                updateLineX(points, vent.x1, vent.x2, vent.y1)
            }
        }

        val size = points.filter{ p -> p.value > 1 }.size

        //GridPrinter.print(points);

        return "$size"
    }

    override fun solvePart2(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day5/$file").iterator()
        if(!input.hasNext()) return ""

        val points: MutableMap<Point, Int> = mutableMapOf()
        val vents: List<Vent> = input.next().ventList

        for (vent in vents){
            if(vent.x1 == vent.x2) {
                updateLineY(points, vent.y1, vent.y2, vent.x1)
            }
            else if(vent.y1 == vent.y2){
                updateLineX(points, vent.x1, vent.x2, vent.y1)
            }
            else if(kotlin.math.abs(vent.y2 - vent.y1) == kotlin.math.abs(vent.x2 - vent.x1)){
                val m = ((vent.y2-vent.y1)/(vent.x2-vent.x1))
                if(vent.x1 < vent.x2) updateDiagLine(points, vent.x1, vent.y1, m, kotlin.math.abs(vent.y2-vent.y1))
                else updateDiagLine(points, vent.x2, vent.y2, m, kotlin.math.abs(vent.y2-vent.y1))
            }
        }

        val size = points.filter{ p -> p.value > 1 }.size

        //GridPrinter.print(points);

        return "$size"
    }
}