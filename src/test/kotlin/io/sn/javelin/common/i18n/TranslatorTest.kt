package io.sn.javelin.common.i18n

import io.sn.io.sn.javelin.common.JavaSystemProperties
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import java.net.URLClassLoader
import java.util.*


class TranslatorTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }


    @Throws(IOException::class)
    private fun createTestBundleWithMissingResourceFallback(): ResourceBundle {
        val f: File = File(JavaSystemProperties.getJavaTempDir(), "test.properties")
        f.createNewFile()
        f.deleteOnExit()

        val fos = FileOutputStream(f)
        val message = ("""
     key=value
     argument.key=value {0} {1}
     apostrophe.key=valuewith''
     ${Translator.MISSING_RESOURCE_PLACEHOLDER}={0}_{1}
     
     """.trimIndent())

        fos.write(message.toByteArray())

        val u: URL = f.parentFile.toURI().toURL()
        val loader: ClassLoader = URLClassLoader(arrayOf(u))

        return ResourceBundle.getBundle("test", Locale.ENGLISH, loader)
    }
}