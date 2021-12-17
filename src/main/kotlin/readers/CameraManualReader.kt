package readers

import data.Axis
import data.CameraManual
import data.Fold
import java.awt.Point
import java.io.File

class CameraManualReader: IReader<CameraManual> {
    override fun read(fileName: String): List<CameraManual> {
        var lines: List<String> = File(fileName).readLines()

        val points: MutableList<Point> = mutableListOf()

        var foldExpr = "fold along ([x,y])=([0-9]+)".toRegex()
        var folds: MutableList<Fold> = mutableListOf()

        for(line in lines){
            if(foldExpr.containsMatchIn(line)){
                val (axis, value) = foldExpr.find(line)!!.destructured
                folds.add(Fold(if(axis == "x") Axis.X else Axis.Y, value.toInt()))
            }
            else if (line.isNotEmpty()){
                val (x, y) = line.split(",")
                points.add(Point(x.toInt(), y.toInt()))
            }
        }

        return listOf(CameraManual(points, folds))
    }
}