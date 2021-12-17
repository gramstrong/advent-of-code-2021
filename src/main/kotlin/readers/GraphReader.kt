package readers

import data.Edge
import data.Graph
import data.Vertex
import java.io.File

class GraphReader: IReader<Graph> {
    override fun read(fileName: String): List<Graph> {
        val edgeStrings: List<String> = StringReader().read(fileName)
        var edges: MutableList<Edge> = mutableListOf()
        for(es in edgeStrings){
            val vertices = es.split("-")
            edges.add(Edge(Vertex(vertices[0]), Vertex(vertices[1])))
        }

        return listOf(Graph(edges))
    }
}