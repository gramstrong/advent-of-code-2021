package data

class Bits(bits: String) {

    constructor(bits: Array<Int>): this(bits.joinToString(""))

    private val size = bits.length
    private val bits: Array<Int> = bits.chunked(1).map{ c -> c.toInt()}.toTypedArray()

    fun getSize(): Int {return size}

    fun get(i: Int): Int {
        return if (i in 0 until size)
            bits[i];
        else 0;
    }

    override fun equals(other: Any?): Boolean {
        if(other !is Bits) return false
        if(getSize() != other.getSize()) return false;
        for(i in 0 until size)
            if(get(i) != other.get(i)) return false;

        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = size.hashCode()
        result = 31 * result + bits.hashCode()
        result = 31 * result + super.hashCode()
        return result
    }

    override fun toString(): String {
        return bits.joinToString("")
    }

    fun toDecimal(): Int {
        return toString().toInt(2)
    }

}