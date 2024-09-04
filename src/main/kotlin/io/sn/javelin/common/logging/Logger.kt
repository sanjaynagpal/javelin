package io.sn.io.sn.javelin.common.logging

import java.util.*


interface Logger {

    /**
     * Log a message at the `DEBUG` level.
     *
     * @param msg the message to be logged
     */
    fun debug(msg: String)

    /**
     * Log a message at the `DEBUG` level.
     * Replace `{}` with the given arguments
     *
     * @param msg       the message to be logged
     * @param args a list of 3 or more arguments for replacement
     */
    fun debug(msg: String, args: Array<Any>?)

    /**
     * Log a message and an exception at the `DEBUG` level.
     *
     * @param msg the message to be logged
     * @param t   the exception to be logged
     */
    fun debug(msg: String, t: Throwable?)

    /**
     * Log a message at the `INFO` level.
     *
     * @param msg the message to be logged
     */
    fun info(msg: String)

    /**
     * Log a message at the `INFO` level.
     * Replace `{}` with the given arguments
     *
     * @param msg       the message to be logged
     * @param args a list of 3 or more arguments for replacement
     */
    fun info(msg: String, args: Array<Any>?)

    /**
     * Log a message and an exception at the `INFO` level.
     *
     * @param msg the message to be logged
     * @param t   the exception to be logged
     */
    fun info(msg: String, t: Throwable?)

    /**
     * Log a message at the `WARN` level.
     *
     * @param msg the message to be logged
     */
    fun warn(msg: String)

    /**
     * Log a message at the `WARN` level.
     * Replace `{}` with the given arguments
     *
     * @param msg       the message to be logged
     * @param args a list of 3 or more arguments for replacement
     */
    fun warn(msg: String, args: Array<Any>?)

    /**
     * Log a message and an exception at the `WARN` level.
     *
     * @param msg the message to be logged
     * @param t   the exception to be logged
     */
    fun warn(msg: String, t: Throwable?)

    /**
     * Log a message at the `ERROR` level.
     *
     * @param msg the message to be logged
     */
    fun error(msg: String)

    /**
     * Log a message at the `ERROR` level.
     * Replace `{}` with the given arguments
     *
     * @param msg       the message to be logged
     * @param args a list of 3 or more arguments for replacement
     */
    fun error(msg: String, args: Array<Any>?)

    /**
     * Log a message and an exception at the `ERROR` level.
     *
     * @param msg the message to be logged
     * @param t   the exception to be logged
     */
    fun error(msg: String, t: Throwable?)

}

fun expand(msg: String, args: Array<Any>?): String {

    if (args == null) {
        return msg
    }

    val argsLength = args.size

    if (argsLength == 0) {
        return msg
    }

    val result = StringBuilder()
    var lastIdx = 0
    for (arg in args) {
        val argString = Objects.toString(arg)
        val idx = msg.indexOf("{}", lastIdx)

        if (idx < 0) {
            break
        }

        result.append(msg, lastIdx, idx).append(argString)
        lastIdx = idx + 2
    }

    result.append(msg.substring(lastIdx))

    return result.toString()
}
