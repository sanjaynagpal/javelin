package io.sn.io.sn.javelin.common.jnlp.version

/**
 * This interface specifies regular expressions to define version ids and version strings
 * according to JSR-56, Appendix A.
 *
 *
 * In case of mental regexp overflow https://regex101.com/ might help.
 */
interface JNLPVersionPatterns {
    companion object {
        // A version id is an exact version that is associated with a resource, such as a JAR file.
        //
        // The version-id used in this specification must conform to the following syntax:
        //
        // <pre>
        //     version-id ::= string ( separator string ) *
        //     string     ::= char ( char ) *
        //     char       ::= any ASCII character except a space, an ampersand, a separator, or a modifier.
        //     separator  ::= "." | "-" | "_"
        // </pre>
        //
        // This specification is implemented by the following regular expressions:
        //
        const val REGEXP_SEPARATOR: String = "[._-]"
        const val REGEXP_CHAR: String = "[^\\s&-._\\*\\+]"
        const val REGEXP_STRING: String = REGEXP_CHAR + "+"
        const val REGEXP_VERSION_ID: String = REGEXP_STRING + "(" + REGEXP_SEPARATOR + REGEXP_STRING + ")*"

        // A version string is a list of version ranges separated by spaces. A version range is either a version-id,
        // a version-id followed by a star (*), a version-id followed by a plus sign (+) , or two version-ranges
        // combined using an ampersand (&). The star means prefix match, the plus sign means this version or
        // greater, and the ampersand means the logical and-ing of the two version-ranges. The syntax of
        // version-strings is:
        //
        // <pre>
        //      version-string     ::=  version-range ( " " version-range) *
        //      version-range      ::=  simple-range ( "&" simple-range) *
        //      simple-range       ::=  version-id | version-id modifier
        //      modifier           ::=  "+" | "*"
        // </pre>
        //
        // This specification is implemented by the following regular expressions:
        //
        const val REGEXP_SPACE: String = "\\s+"
        const val REGEXP_MODIFIER: String = "[\\*\\+]"
        const val REGEXP_SIMPLE_RANGE: String = REGEXP_VERSION_ID + REGEXP_MODIFIER + "?"
        const val REGEXP_VERSION_RANGE: String = REGEXP_SIMPLE_RANGE + "(&" + REGEXP_SIMPLE_RANGE + ")*"
        const val REGEXP_VERSION_STRING: String =
            REGEXP_VERSION_RANGE + "(" + REGEXP_SPACE + REGEXP_VERSION_RANGE + ")*"
    }
}