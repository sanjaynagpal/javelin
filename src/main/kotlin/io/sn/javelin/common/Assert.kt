package io.sn.io.sn.javelin.common


object Assert {
    private const val NOT_NULL_MSG_FORMAT = "Argument '%s' may not be null"
    private const val NOT_EMPTY_MSG_FORMAT = "Argument '%s' may not be empty"

    /**
     * Checks that the specified `value` is null and throws [java.lang.NullPointerException] with a customized error message if it is.
     *
     * @param value        the value to be checked.
     * @param argumentName the name of the argument to be used in the error message.
     * @param <T>          type of the value
     * @return the `value`.
    </T> */
    fun <T> requireNonNull(value: T?, argumentName: String?): T {
        if (argumentName == null) {
            throw NullPointerException(String.format(NOT_NULL_MSG_FORMAT, "argumentName"))
        }
        if (value == null) {
            throw NullPointerException(String.format(NOT_NULL_MSG_FORMAT, argumentName))
        }
        return value
    }

    /**
     * Checks that the specified `str` `blank`, throws [IllegalArgumentException] with a customized error message if it is.
     *
     * @param str          the value to be checked.
     * @param argumentName the name of the argument to be used in the error message.
     * @return the `str`.
     * @throws java.lang.NullPointerException     if `str` is null.
     * @throws java.lang.IllegalArgumentException if `str` is blank.
     * @see .requireNonNull
     * @see StringUtils.isBlank
     */
    fun requireNonBlank(str: String, argumentName: String?): String {
        requireNonNull(str, argumentName)
        require(str.isNotBlank()) { String.format(NOT_EMPTY_MSG_FORMAT, argumentName) }
        return str
    }
}