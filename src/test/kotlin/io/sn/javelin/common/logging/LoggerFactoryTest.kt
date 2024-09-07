package io.sn.javelin.common.logging

import io.sn.io.sn.javelin.common.logging.LoggerFactory
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class LoggerFactoryTest {

    @Test
    fun getLogger() {
        val logger = LoggerFactory.getLogger(String::class.java)
        logger.warn("Logger Factory WARNING log test")
    }
}