package io.sn.io.sn.javelin.common.logging

object LoggerFactory {

    private val factory = SystemOutLoggerFactory

    fun getLogger(clazz: Class<*>) : Logger {
        return factory.getLogger(clazz)
    }
}

interface LoggerFactoryImpl {
    fun getLogger(forClass: Class<*>): Logger
}