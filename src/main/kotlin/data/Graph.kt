package data

import java.util.*

data class Vertex(var name: String){
    fun isLarge(): Boolean = name == name.uppercase(Locale.getDefault())
}

data class Edge(var v1: Vertex, var v2: Vertex)

data class Graph(var edges: List<Edge>){
    private var vertices: MutableMap<Vertex, MutableList<Vertex>> = mutableMapOf()

    fun getStart(): Vertex {
        for((k, v) in vertices)
            if(k.name == "start") return k

        return Vertex("start")
    }

    fun getEnd(): Vertex {
        for((k, v) in vertices)
            if(k.name == "end") return k

        return Vertex("end")
    }

    fun twoSmallVertexInPath(path: ArrayDeque<Vertex>) = path.fold(false) { acc, v -> acc || (!v.isLarge() && path.count() {it == v} == 2) }

    fun traverse(curr: Vertex, path: ArrayDeque<Vertex>, maxSmallVertex: Int): MutableList<ArrayDeque<Vertex>>{
        var paths: MutableList<ArrayDeque<Vertex>> = mutableListOf()
        path.push(curr)

        val siblings = vertices.get(curr)

        if (siblings != null) {
            for(sibling in siblings){
                var pathCount = path.count { it == sibling }

                if(sibling == getEnd()){
                    var finishedPath = ArrayDeque(path)
                    finishedPath.push(getEnd())
                    paths.add(finishedPath)
                }
                else if(sibling != getStart() && (pathCount < maxSmallVertex) || sibling.isLarge()){
                    if(!twoSmallVertexInPath(path) || pathCount < 1 || sibling.isLarge()){
                        for(siblingPath in traverse(sibling, ArrayDeque(path), maxSmallVertex)) {
                            paths.add(siblingPath)
                        }
                    }
                }
            }
        }

        return paths
    }

    init {
        for (e in edges) {
            if (vertices.contains(e.v1)) {
                vertices[e.v1]?.add(e.v2)
            }
            else{
                vertices[e.v1] = mutableListOf(e.v2)
            }
            if (vertices.contains(e.v2)){
                vertices[e.v2]?.add(e.v1)
            }
            else{
                vertices[e.v2] = mutableListOf(e.v1)
            }
        }
    }
}

