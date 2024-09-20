package io.sn.io.sn.javelin.common.jnlp.version

/**
 * Enum of all modifiers as defined for [VersionRange]s as defined by JSR-56 Specification, Appendix A.
 */
enum class VersionModifier(private val symbol: String) {
    NONE(""),
    PLUS("+"),
    ASTERISK("*"),
    AMPERSAND("&");

    fun symbol(): String {
        return symbol
    }
}