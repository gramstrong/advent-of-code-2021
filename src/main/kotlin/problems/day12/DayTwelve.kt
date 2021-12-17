package problems.day12
import data.Graph
import data.Vertex
import problems.ISolver
import readers.IReader
import java.util.ArrayDeque

class DayTwelve(private val reader: IReader<Graph>) : ISolver {

    override fun solvePart1(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day12/$file").iterator()
        var paths: MutableList<ArrayDeque<Vertex>> = mutableListOf()

        if(input.hasNext()){
            var graph = input.next()
            paths = graph.traverse(graph.getStart(), ArrayDeque(),1)
        }

        return "${paths.size}"
    }

    override fun solvePart2(file: String): String {
        val input = reader.read("src/main/kotlin/problems/day12/$file").iterator()
        var paths: MutableList<ArrayDeque<Vertex>> = mutableListOf()

        if(input.hasNext()){
            var graph = input.next()
            paths = graph.traverse(graph.getStart(), ArrayDeque(),2)
        }

        return "${paths.size}"
    }
}