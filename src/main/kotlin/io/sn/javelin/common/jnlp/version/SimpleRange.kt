package io.sn.io.sn.javelin.common.jnlp.version

import io.sn.io.sn.javelin.common.Assert
import io.sn.io.sn.javelin.common.jnlp.version.JNLPVersionPatterns.Companion.REGEXP_MODIFIER
import io.sn.io.sn.javelin.common.jnlp.version.JNLPVersionPatterns.Companion.REGEXP_SIMPLE_RANGE
import java.util.*


/**
 * A simple-range is either a version-id, a version-id followed by a star (*) or a version-id followed by a plus sign (+).
 * The star means prefix match, the plus sign means this version or greater.
 *
 *
 * The syntax of version-strings is:
 *
 * <pre>
 * simple-range       ::=  version-id | version-id modifier
 * modifier           ::=  "+" | "*"
</pre> *
 *
 *
 * See JSR-56 Specification, Appendix A.
 *
 * @see JNLPVersionPatterns
 */
internal class SimpleRange private constructor(private val versionId: VersionId, private val modifier: VersionModifier) {

    val isExactVersion: Boolean
        /**
         * Checks whether this simple-range represents a single version-id without any postfix modifiers.
         *
         * @return `true` if this simple-range does not have any modifiers, `false` otherwise.
         */
        get() = modifier === VersionModifier.NONE

    /**
     * @return the exact version if this simple-range is one
     * @throws IllegalStateException if this simple-range is not one
     */
    fun getExactVersion(): VersionId {
        if (!isExactVersion) {
            throw IllegalStateException("${toString()} is not an exact version")
        }
        return versionId
    }

    fun hasPrefixMatchModifier(): Boolean {
        return modifier === VersionModifier.ASTERISK
    }

    fun hasGreaterThanOrEqualMatchModifier(): Boolean {
        return modifier === VersionModifier.PLUS
    }

    /**
     * Provides a string representation of this [SimpleRange].
     *
     * @return a string representation of this simple-range
     */
    override fun toString(): String {
        return versionId.toString() + modifier.symbol()
    }

    fun isEqualTo(otherSimpleRange: SimpleRange?): Boolean {
        return equals(otherSimpleRange)
    }

    override fun equals(otherSimpleRange: Any?): Boolean {
        if (otherSimpleRange == null || otherSimpleRange.javaClass != SimpleRange::class.java) {
            return false
        }

        val other = otherSimpleRange as SimpleRange
        return versionId == other.versionId && modifier == other.modifier
    }

    /**
     * Check if this simple-range contains the given `versionId`.
     *
     * @param otherVersionId a version-id
     * @return `true` if this version-range contains `versionId`, `false` otherwise.
     */
    fun contains(otherVersionId: String): Boolean {
        return contains(VersionId.fromString(otherVersionId))
    }

    /**
     * Check if this simple-range contains the given `versionId`.
     *
     * @param otherVersionId a version-id
     * @return `true` if this version-range contains `versionId`, `false` otherwise.
     */
    fun contains(otherVersionId: VersionId): Boolean {
        Assert.requireNonNull(otherVersionId, "otherVersionId")

        if (isExactVersion) {
            return versionId.isEqualTo(otherVersionId)
        }

        if (hasPrefixMatchModifier()) {
            return versionId.isPrefixMatchOf(otherVersionId)
        }

        if (hasGreaterThanOrEqualMatchModifier()) {
            return versionId.isLessThan(otherVersionId) || versionId.isEqualTo(otherVersionId)
        }

        throw IllegalStateException("Simple range is neither exact, nor prefix, nor less")
    }

    override fun hashCode(): Int {
        var result = versionId.hashCode()
        result = 31 * result + modifier.hashCode()
        return result
    }

    companion object {
        /**
         * Construct a simple-range by the given `simpleRange`.
         *
         * @param simpleRange a simple-range
         * @return a SimpleRange
         */
        fun fromString(simpleRange: String): SimpleRange {
            Assert.requireNonNull(simpleRange, "simpleRange")

            if (!simpleRange.matches(REGEXP_SIMPLE_RANGE.toRegex())) {
                throw IllegalArgumentException(
                    "${simpleRange} is not a valid simple-range according to JSR-56, Appendix A."
                )
            }

            val versionId: VersionId = extractVersionId(simpleRange)
            val modifier: VersionModifier = extractModifier(simpleRange)

            return SimpleRange(versionId, modifier)
        }

        private fun extractModifier(simpleRange: String): VersionModifier {
            for (modifier in Arrays.asList(VersionModifier.PLUS, VersionModifier.ASTERISK)) {
                if (simpleRange.endsWith(modifier.symbol())) {
                    return modifier
                }
            }
            return VersionModifier.NONE
        }

        private fun extractVersionId(simpleRange: String): VersionId {
            val exactId: String = simpleRange.replace(("$REGEXP_MODIFIER$").toRegex(), "")
            return VersionId.fromString(exactId)
        }
    }
}