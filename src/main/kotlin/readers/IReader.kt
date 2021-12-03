package readers

interface IReader<out T> {
    fun read(fileName: String): List<T>
}