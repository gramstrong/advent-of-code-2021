package data

import java.awt.Point

enum class Axis{
    X,
    Y
}

data class Fold(val axis: Axis, val crease: Int)

data class CameraManual(var points: List<Point>, val folds: List<Fold>){
    var timesFolded = 0
    var initialBounds: Point

    init {
        var x = 0
        var y = 0

        for(i in timesFolded until folds.size){
            if(folds[i].axis == Axis.X && folds[i].crease*2+1 > x) x = folds[i].crease*2+1
            if(folds[i].axis == Axis.Y && folds[i].crease*2+1 > y) y = folds[i].crease*2+1
        }
        initialBounds = Point(x,y)
    }

    fun isMarked(x: Int, y: Int) = points.fold(false) { acc, p -> acc || (p.x == x && p.y == y) }

    fun getBounds(): Point{
        var x = initialBounds.x
        var y = initialBounds.y
        for(i in 0 until timesFolded){
            if(folds[i].axis == Axis.X) x -= folds[i].crease+1
            if(folds[i].axis == Axis.Y) y -= folds[i].crease+1
        }

        return Point(x,y)
    }

    fun getCurrFold(): Fold = folds[timesFolded]

    fun doneFolding(): Boolean = timesFolded == folds.size

    fun fold(){
        val bounds = getBounds()
        val fold = getCurrFold()
        timesFolded++

        for(point in points){
            if(fold.axis == Axis.Y){
                if (point.y > fold.crease)
                    point.y = (bounds.y-point.y-1)
            }
            else if(fold.axis == Axis.X)
                if(point.x > fold.crease)
                    point.x = (bounds.x-point.x-1)
        }

        points = points.distinct()
    }
}