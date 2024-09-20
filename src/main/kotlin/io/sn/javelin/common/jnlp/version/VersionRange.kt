package io.sn.io.sn.javelin.common.jnlp.version

import io.sn.io.sn.javelin.common.jnlp.version.JNLPVersionPatterns.Companion.REGEXP_VERSION_RANGE
import java.util.*
import java.util.stream.Collectors


/**
 * A version-range is either a version-id, a version-id followed by a star (*),
 * a version-id followed by a plus sign (+) , or two version-ranges combined using an ampersand (&amp;).
 * The star means prefix match, the plus sign means this version or greater,
 * and the ampersand means the logical and-ing of the two version-ranges.
 *
 *
 * The syntax of version-strings is:
 *
 * <pre>
 * version-string     ::=  version-range ( " " element) *
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
class VersionRange private constructor(simpleRanges: Array<SimpleRange>) {
    private val simpleRanges: Array<SimpleRange> = simpleRanges

    val isCompoundVersion: Boolean
        /**
         * @return `true` if this version-range is a compound range using an ampersand (&amp;), false otherwise
         */
        get() = simpleRanges.size > 1

    val isExactVersion: Boolean
        /**
         * Checks whether this version-range represents a plain (exact) version without any postfix modifiers.
         *
         * @return `true` if this version-range does not have any modifiers, `false` otherwise.
         */
        get() = simpleRanges.size == 1 && simpleRanges[0].isExactVersion

    /**
     * @return the exact version if this version-range is one
     * @throws IllegalStateException if this version-range is not one
     */
    fun getExactVersion(): VersionId {
        if (!isExactVersion) {
            throw IllegalStateException("${toString()} is not an exact version")
        }
        return simpleRanges[0].getExactVersion()
    }

    /**
     * Checks whether this version-range represents a range with a prefix match modifier.
     *
     * @return `true` if this version-range is not a compound range
     * and its only simple range has a prefix match modifier, `false` otherwise.
     */
    fun hasPrefixMatchModifier(): Boolean {
        return simpleRanges.size == 1 && simpleRanges[0].hasPrefixMatchModifier()
    }

    /**
     * Checks whether this version-range represents a range with a greater or equal modifier.
     *
     * @return `true` if this version-range is not a compound range
     * and its only simple range has a greater or equal modifier, `false` otherwise.
     */
    fun hasGreaterThanOrEqualMatchModifier(): Boolean {
        return simpleRanges.size == 1 && simpleRanges[0].hasGreaterThanOrEqualMatchModifier()
    }

    /**
     * Check if this version-range contains the given `versionId`.
     *
     * @param versionId a version-id
     * @return `true` if this version-range contains `versionId`, `false` otherwise.
     */
    fun contains(versionId: String): Boolean {
        return contains(VersionId.fromString(versionId))
    }

    /**
     * Check if this version-range contains the given `versionId`.
     *
     * @param versionId a version-id
     * @return `true` if this version-range contains `versionId`, `false` otherwise.
     */
    fun contains(versionId: VersionId): Boolean {
        return Arrays.stream(simpleRanges).allMatch { simpleRange -> simpleRange.contains(versionId) }
    }

    fun isEqualTo(otherVersionRange: VersionRange?): Boolean {
        return equals(otherVersionRange)
    }

    override fun equals(otherVersionRange: Any?): Boolean {
        if (otherVersionRange == null || otherVersionRange.javaClass != VersionRange::class.java) {
            return false
        }
        val other = otherVersionRange as VersionRange

        return Arrays.equals(simpleRanges, other.simpleRanges)
    }

    /**
     * Provides a string representation of this [VersionRange].
     *
     * @return a string representation of this version-range
     */
    override fun toString(): String {
        return Arrays.stream(simpleRanges)
            .map(SimpleRange::toString)
            .collect(Collectors.joining("&"))
    }

    companion object {
        /**
         * Construct a version-range by the given `versionRange`.
         *
         * @param versionRange a version-range
         * @return a version-id
         */
        fun fromString(versionRange: String): VersionRange {
            TODO()
        }
    }
}