package io.sn.io.sn.javelin.common.jnlp.version

import io.sn.io.sn.javelin.common.Assert
import java.util.*
import java.util.stream.Collectors


/**
 * A version-string is a list of version-ranges separated by spaces. A version-range is either a version-id,
 * a version-id followed by a star (*), a version-id followed by a plus sign (+) , or two version-ranges
 * combined using an ampersand (&amp;). The star means prefix match, the plus sign means this version or
 * greater, and the ampersand means the logical and-ing of the two version-ranges.
 *
 *
 * The syntax of version-strings is:
 *
 * <pre>
 * version-string     ::=  version-range ( " " version-range) *
 * version-range      ::=  simple-range ( "&amp;" simple-range) *
 * simple-range       ::=  version-id | version-id modifier
 * modifier           ::=  "+" | "*"
</pre> *
 *
 *
 * See JSR-56 Specification, Appendix A.
 *
 * @see JNLPVersionPatterns
 */
class VersionString private constructor(val versionRanges: Array<VersionRange>) {

    /**
     * @return `true` if this version-string consists of only a single version-range, `false` otherwise.
     */
    fun containsSingleVersionRange(): Boolean {
        return versionRanges.size == 1
    }

    val isExactVersion: Boolean
        /**
         * @return `true` if this version-string consists of a single version-range which is an exact match, `false` otherwise.
         */
        get() = versionRanges.size == 1 && versionRanges[0].isExactVersion

    /**
     * @return the exact version if this version-string is one
     * @throws IllegalStateException if this version-string is not none
     */
    fun getExactVersion(): VersionId {
        if (!isExactVersion) {
            throw IllegalStateException("${toString()} is not an exact version")
        }
        return versionRanges[0].getExactVersion()
    }

    /**
     * Checks if this version-string (list of exact version-ids or version ranges) contains the given `versionId`.
     *
     * @param versionId a version-id
     * @return `true` if this version-string contains the given `versionId`, `false` otherwise
     */
    fun contains(versionId: String): Boolean {
        return contains(VersionId.fromString(versionId))
    }

    /**
     * Checks if this version-string (list of exact version-ids or version ranges) contains the given `versionId`.
     *
     * @param versionId a version-id
     * @return `true` if this version-string contains the given `versionId`, `false` otherwise
     */
    fun contains(versionId: VersionId): Boolean {
        return Arrays.stream(versionRanges).anyMatch { range -> range.contains(versionId) }
    }

    /**
     * Compares two version-ids in the context of this version-string.
     * This implementation follows the specification of a [java.util.Comparator].
     *
     *
     * If two or more version-id match the given version-string,
     * the JNLP Client should use the one matching the earlier version-range in the version-string.
     *
     *
     * If two or more version-id match a given version-range,
     * the JNLP Client should use the one with the highest version-id.
     *
     *
     * See JSR-56 Specification, Appendix A.
     *
     * @param versionId1 a version-id
     * @param versionId2 a version-id
     * @return a negative int if versionId1 is less than versionId2,
     * a positive int if versionId1 is after than versionId2 or zero if they are equal.
     */
    fun compare(versionId1: VersionId, versionId2: VersionId): Int {
        Assert.requireNonNull(versionId1, "versionId1")
        Assert.requireNonNull(versionId2, "versionId2")

        val idxOfId1 = indexOfFirstRangeContaining(versionId1)
        val idxOfId2 = indexOfFirstRangeContaining(versionId2)
        val diff = idxOfId2 - idxOfId1

        return if (diff != 0) diff else versionId1.compareTo(versionId2)
    }

    private fun indexOfFirstRangeContaining(versionId: VersionId): Int {
        val length = versionRanges.size
        for (i in 0 until length) {
            if (versionRanges[i].contains(versionId)) {
                return i
            }
        }
        return length
    }

    override fun equals(otherVersionString: Any?): Boolean {
        if (otherVersionString == null || otherVersionString.javaClass != VersionString::class.java) {
            return false
        }
        val other = otherVersionString as VersionString

        return Arrays.equals(versionRanges, other.versionRanges)
    }

    /**
     * Provides string representation of this version-string.
     *
     * @return a string representation of this version-string
     */
    override fun toString(): String {
        return Arrays.stream(versionRanges)
            .map(VersionRange::toString)
            .collect(Collectors.joining(" "))
    }

    companion object {
        var ANY_VERSION: VersionString = fromString("0+")

        /**
         * Construct a version-string by the given `versionString`.
         *
         * @param versionString a version-string
         * @return a versionString
         */
        fun fromString(versionString: String): VersionString {
            TODO()
        }
    }
}
