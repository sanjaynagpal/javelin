package io.sn.io.sn.javelin.common.jnlp.version

import io.sn.io.sn.javelin.common.asciiSum
import io.sn.io.sn.javelin.common.expandAndFill
import io.sn.io.sn.javelin.common.isLessThan
import io.sn.io.sn.javelin.common.isNumeric
import io.sn.io.sn.javelin.common.jnlp.version.JNLPVersionPatterns.Companion.REGEXP_SEPARATOR
import io.sn.io.sn.javelin.common.jnlp.version.JNLPVersionPatterns.Companion.REGEXP_VERSION_ID
import kotlin.math.max
import kotlin.math.min


/**
 * A version-id specifies the version that is associated with a resource, such as a JAR file.
 * The version-id used in this specification must conform to the following syntax:
 *
 * <pre>
 * version-id ::= string ( separator string ) *
 * string     ::= char ( char ) *
 * char       ::= any ASCII character except a space, an ampersand, a separator, or a modifier.
 * separator  ::= "." | "-" | "_"
 * modifier   ::=  "+" | "*"
</pre> *
 *
 *
 * See JSR-56 Specification, Appendix A.
 *
 * @see JNLPVersionPatterns
 */
data class VersionId(val versionId: String) : Comparable<VersionId> {
    /**
     * A version-id can be described as a tuple of values. A version-id string is broken in parts for each
     * separator ('.', '-', or '_').
     * For example, "1.3.0-rc2-w" becomes (1,3,0,rc2,w), and "1.2.2-001" becomes (1,2,2,001).
     */
    val tuple =
        versionId
            .split(REGEXP_SEPARATOR.toRegex())
            .map { x -> if (x.isNumeric()) x.toInt().toString() else x }
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()

    /**
     * Static object associated with the data class
     */
    companion object {
        /**
         * Construct a version-id by the given `versionId`.
         *
         * @param versionId a version-id
         * @return a version-id
         */
        fun fromString(versionId: String): VersionId {

            if (!versionId.matches(REGEXP_VERSION_ID.toRegex())) {
                throw IllegalArgumentException("$versionId is not a valid version-id according to JSR-56, Appendix A.")
            }

            return VersionId(versionId)
        }
    }

    /**
     * Compares whether this version-id is equal to the `otherVersionId`.
     *
     * @param other a version-id
     * @return `true` if this equals to `otherVersionId`, `false` otherwise.
     */
    fun isEqualTo(other: VersionId): Boolean {
        // normalize the size of both tuples
        val (a, b) = normalizeTuple(other)

        return a.contentEquals(b)
    }

    private fun normalizeTuple(other: VersionId): Pair<Array<String>, Array<String>> {
        val maxSize = max(tuple.size, other.tuple.size)
        val a = if (tuple.size < maxSize) tuple.expandAndFill(maxSize) else tuple
        val b = if (other.tuple.size < maxSize) other.tuple.expandAndFill(maxSize) else other.tuple
        return Pair(a, b)
    }


    override fun compareTo(other: VersionId): Int {
        if (isEqualTo(other)) {
            return 0
        }

        return if (isLessThan(other)) -1 else 1
    }

    /**
     * A is a prefix matches of B if, when represented as tuples, the elements of A are the same as the
     * first elements of B. The padding with 0 (zero element) entries ensures that B has at least as
     * many elements as A.
     *
     *
     * For example, given the above definition "1.2.1" will be a prefix matches to "1.2.1-004", but not
     * to "1.2.0" or "1.2.10". The padding step ensures that "1.2.0.0" is a prefix of "1.2". Note that
     * prefix matching and ordering are distinct: "1.3" is greater than "1.2", and less than "1.4",
     * but not a prefix of either.
     *
     *
     * See JSR-56 Specification, Appendix A.2 Prefix Match.
     *
     * @param other a version-id
     * @return `true` if this version-id is a prefix matches of `otherVersionId`, `false` otherwise.
     */
    fun isPrefixMatchOf(other: VersionId): Boolean {
        val minSize = min(tuple.size, other.tuple.size)
        return tuple.asciiSum(minSize) == other.tuple.asciiSum(minSize)
    }

    /**
     * Compares whether this version-id is less than `otherVersionId`.
     *
     * A is less than B if, when represented as normalized tuples, there exists some element
     * of A which is less than the corresponding element of B, and all earlier elements of A
     * are the same as in B (see JSR-56 Specification, Appendix A.1 Ordering).
     *
     * Two numeric elements are compared numerically. Two alphanumeric elements are compared
     * lexicographically according to the Unicode value of the individual characters. When one
     * element is numeric and the other is alphanumeric, the alphanumeric element is greater
     * than the numeric element. Numeric elements have lower precedence than non-numeric
     * elements.
     *
     *
     * See JSR-56 Specification, Appendix A
     *
     * @param other a version-id
     * @return `true` if this version-id is less than `otherVersionId`, `false` otherwise.
     */
    fun isLessThan(other: VersionId): Boolean {
        val maxSize = max(tuple.size, other.tuple.size)
        val t1 = tuple.expandAndFill(maxSize)
        val t2 = other.tuple.expandAndFill(maxSize)
        var isLess = false
        for (i in 0 until maxSize) {
            val a = t1[i].asciiSum()
            val b = t2[i].asciiSum()
            if (a != b) {
                if (a < b) isLess = true
                else isLess = false
                break
            }
        }
        return isLess
    }

}