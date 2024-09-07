package io.sn.io.sn.javelin.common.logging

import io.sn.io.sn.javelin.common.exceptionAsString
import java.util.*


object SystemOutLoggerFactory : LoggerFactoryImpl {

    private val outStream = System.out

    override fun getLogger(forClass: Class<*>): Logger {
        return SystemOutLogger(forClass)
    }

    class SystemOutLogger(private val clazz: Class<*>) : Logger {

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
            log(Level.DEBUG.prefix, msg, t)
        }

        override fun info(msg: String) {
            log(Level.INFO.prefix, msg)
        }

        override fun info(msg: String, args: Array<Any>?) {
            log(Level.INFO.prefix, expand(msg, args))
        }

        override fun info(msg: String, t: Throwable?) {
            log(Level.INFO.prefix, msg, t)
        }

        override fun warn(msg: String) {
            log(Level.WARNING.prefix, msg)
        }

        override fun warn(msg: String, args: Array<Any>?) {
            log(Level.WARNING.prefix, expand(msg, args))
        }

        override fun warn(msg: String, t: Throwable?) {
            log(Level.WARNING.prefix, msg, t)
        }

        override fun error(msg: String) {
            log(Level.ERROR.prefix, msg)
        }

        override fun error(msg: String, args: Array<Any>?) {
            log(Level.ERROR.prefix, expand(msg, args))
        }

        override fun error(msg: String, t: Throwable?) {
            log(Level.ERROR.prefix, msg, t)
        }

    }

}

