package data

data class GammaEpsilon(val gamma: Bits, val epsilon: Bits) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GammaEpsilon

        if (gamma != other.gamma) return false
        if (epsilon != other.epsilon) return false

        return true
    }

    override fun hashCode(): Int {
        var result = gamma.hashCode()
        result = 31 * result + epsilon.hashCode()
        return result
    }
}