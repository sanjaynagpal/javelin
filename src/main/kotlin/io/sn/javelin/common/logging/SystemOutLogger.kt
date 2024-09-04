package io.sn.io.sn.javelin.common.logging

import io.sn.io.sn.javelin.common.exceptionAsString
import java.util.*


class SystemOutLogger(private val clazz: Class<*>) : Logger {

    private val outStream = System.out

    fun log(level: String, msg: String, t: Throwable? = null) {
        val isMultiLine = msg.contains("\n") || (t != null)
        val separator = if (isMultiLine) "\n" else " "
        val header = "${Date()} $level ${clazz.name} :"
        val line = (header + separator + msg) + (if (t != null) separator + t.exceptionAsString() else "")
        outStream.println(line)
    }

    enum class Level(val prefix: String) {
        DEBUG("[DEBUG  ]"),
        INFO("[INFO   ]"),
        WARNING("[WARNING]"),
        ERROR("[ERROR  ]")
    }

    override fun debug(msg: String) {
        log(Level.DEBUG.prefix, msg)
    }

    override fun debug(msg: String, args: Array<Any>?) {
        log(Level.DEBUG.prefix, expand(msg, args))
    }

    override fun debug(msg: String, t: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun info(msg: String) {
        TODO("Not yet implemented")
    }

    override fun info(msg: String, args: Array<Any>?) {
        TODO("Not yet implemented")
    }

    override fun info(msg: String, t: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun warn(msg: String) {
        TODO("Not yet implemented")
    }

    override fun warn(msg: String, args: Array<Any>?) {
        TODO("Not yet implemented")
    }

    override fun warn(msg: String, t: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun error(msg: String) {
        TODO("Not yet implemented")
    }

    override fun error(msg: String, args: Array<Any>?) {
        TODO("Not yet implemented")
    }

    override fun error(msg: String, t: Throwable?) {
        TODO("Not yet implemented")
    }

}