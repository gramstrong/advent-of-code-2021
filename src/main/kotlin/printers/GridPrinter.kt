package printers

import java.awt.Point

class GridPrinter {
    companion object{
        fun print(points: MutableMap<Point, Int>){
            for(y in 0..9) {
                for (x in 0..9) {
                    if (points[Point(x, y)] != null) print("${points.get(Point(x, y))}|")
                    else print(".|")
                }
                print("\n")
            }
            print("\n")
        }
    }
}