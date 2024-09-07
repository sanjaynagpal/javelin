package io.sn.javelin.common.logging

import io.sn.io.sn.javelin.common.logging.SystemOutLoggerFactory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SystemOutLoggerFactoryTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun getLogger() {
        val logger = SystemOutLoggerFactory.getLogger(String::class.java)
        logger.info("Testing info log to system out")
        logger.warn("Testing warning log to system out")
        logger.error("Testing error log to system.out")
        logger.debug("Testing debug log to system.out")
    }
}