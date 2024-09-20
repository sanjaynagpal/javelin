package io.sn.javelin.common.jnlp.version

import io.sn.io.sn.javelin.common.asciiSum
import io.sn.io.sn.javelin.common.expandAndFill
import io.sn.io.sn.javelin.common.isLessThan
import io.sn.io.sn.javelin.common.isNumeric
import io.sn.io.sn.javelin.common.jnlp.version.VersionId
import kotlin.math.max
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class VersionIdTest {

    @Test
    fun `check version id initialization`() {
        assertEquals("1.3.0-rc2-w", VersionId.fromString("1.3.0-rc2-w").versionId)
        assertEquals("15.2.2_21.05.2019_11:43:34", VersionId.fromString("15.2.2_21.05.2019_11:43:34").versionId)
    }

    @Test
    fun `check tuple equality`() {
        val t = VersionId.fromString("1.3.0-rc2-w").tuple
        t.forEach { x -> println(x) }

        val v1 = VersionId.fromString("1.3.0-001").tuple
        val v2 = VersionId.fromString("1.3.0.2").tuple
        val a = v1.contentEquals(v2)
        println(a)

        val v1s = v1.map { x -> if (x.isNumeric()) x.toInt().toString() else x }.toTypedArray()
        v1s.forEach { println(it) }

        println("004".isNumeric())

        println("build41".asciiSum())
        println("build42".asciiSum())

        println("V1 ascii sum : ${v1.asciiSum()}")
        println("V2 ascii sum : ${v2.asciiSum()}")

        println("V1 < V2 : ${v1.isLessThan(v2)}")
        println("V2 < V1 : ${v2.isLessThan(v1)}")
    }

    @Test
    fun `array expansion`() {
        val mz = max(2,3)
        val v1 = VersionId.fromString("1.0")
        val xv1 = v1.tuple.expandAndFill(mz)
        println(xv1.joinToString(separator = "."))
    }

    @Test
    fun `find first less than in tuple`() {
        val x = versionId("1.1").tuple
        val y = versionId("1.0.2").tuple

        val maxSize = max(x.size, y.size)
        val t1 = x.expandAndFill(maxSize)
        val t2 = y.expandAndFill(maxSize)
        var isLess = false
        for (i in 0 until maxSize) {
            println("${t1[i].asciiSum()} : ${t2[i].asciiSum()}")
            val a = t1[i].asciiSum()
            val b = t2[i].asciiSum()
            if (a != b) {
                if (a < b) isLess = true
                else isLess = false
                break
            }
        }
        println("${t1.joinToString(".")} < ${t2.joinToString(".")} = $isLess")
    }

    @Test
    fun `check equality of version id`() {
        assertTrue(versionId("1.0").isEqualTo(versionId("1")));
        assertTrue(versionId("1-0").isEqualTo(versionId("1")));
        assertTrue(versionId("1_0").isEqualTo(versionId("1")));
        assertTrue(versionId("1").isEqualTo(versionId("1.0")));
        assertTrue(versionId("1.0").isEqualTo(versionId("1.0")));
        assertTrue(versionId("1.0").isEqualTo(versionId("1.0.0-0")));
        assertTrue(versionId("1.0.0_0").isEqualTo(versionId("1.0.0")));
        assertTrue(versionId("1.3").isEqualTo(versionId("1.3.0")));
        assertTrue(versionId("1.3.0").isEqualTo(versionId("1.3")));
        assertTrue(versionId("1.2.2.4").isEqualTo(versionId("1.2.2-004")));

        // not a match
        assertFalse(versionId("1.0.4").isEqualTo(versionId("1.0")));
        assertFalse(versionId("1.0.4").isEqualTo(versionId("1.4")));
        assertFalse(versionId("1.0-build42").isEqualTo(versionId("1.0.0-build42")));
        assertFalse(versionId("1.0-b42").isEqualTo(versionId("1.0-B42")));

    }
    @Test
    fun `prefix match checks`() {
        val v1 = VersionId.fromString("1.5")
        val v2 = VersionId.fromString("1.6")
        println(v1.tuple.asciiSum(v1.tuple.size))
        println(v2.tuple.asciiSum(v1.tuple.size))
        assertTrue(versionId("1.0").isPrefixMatchOf(versionId("1.0.0")))
        assertTrue(versionId("2.0").isPrefixMatchOf(versionId("2.0.1")));
        assertTrue(versionId("1.4").isPrefixMatchOf(versionId("1.4.6")));
        assertTrue(versionId("1.4.3").isPrefixMatchOf(versionId("1.4.3-009")));
        assertTrue(versionId("1.2.1").isPrefixMatchOf(versionId("1.2.1-004")));
        assertTrue(versionId("1.2.0.0").isPrefixMatchOf(versionId("1.2")));
        assertTrue(versionId("1.2.2.4").isPrefixMatchOf(versionId("1.2.2-004_beta")));
        // no prefix match
        assertFalse(versionId("1").isPrefixMatchOf(versionId("2.1.0")));
        assertFalse(versionId("1.5").isPrefixMatchOf(versionId("1.6")));
        assertFalse(versionId("1.5").isPrefixMatchOf(versionId("2.0.0")));
        assertFalse(versionId("1.2").isPrefixMatchOf(versionId("1.3")));
        assertFalse(versionId("1.2.1").isPrefixMatchOf(versionId("1.2.10")));
    }

    @Test
    fun `version is less than`() {
        // less than
        assertTrue(versionId("1").isLessThan(versionId("2")));
        assertTrue(versionId("1").isLessThan(versionId("1.1")));
        assertTrue(versionId("1.0").isLessThan(versionId("1.1")));
        assertTrue(versionId("1.1.0").isLessThan(versionId("1.1.1")));
        assertTrue(versionId("1.1").isLessThan(versionId("1.1.1")));
        assertTrue(versionId("1.0.0-build42").isLessThan(versionId("1.0.1")));
        assertTrue(versionId("1.4.2").isLessThan(versionId("1.4.5")));

        // numeric elements have lower precedence than non-numeric elements
        assertTrue(versionId("1").isLessThan(versionId("A")));
        assertTrue(versionId("1.0.1").isLessThan(versionId("1.0.A")));
        assertTrue(versionId("1.0.A").isLessThan(versionId("1.0.B")));
        assertTrue(versionId("1.0.B").isLessThan(versionId("1.1.A")));
        assertTrue(versionId("1.0.A").isLessThan(versionId("1.0.ABC")));
        assertTrue(versionId("1.0.0-build41").isLessThan(versionId("1.0.0-build42")));
        assertTrue(versionId("1.0.0-42").isLessThan(versionId("1.0.0-build42")));

        // not less than
        assertFalse(versionId("1.0").isLessThan(versionId("1")));
        assertFalse(versionId("1.0").isLessThan(versionId("1.0")));
        assertFalse(versionId("1.1").isLessThan(versionId("1.0")));
        assertFalse(versionId("1.1").isLessThan(versionId("1.0.2")));
        assertFalse(versionId("1.0.1").isLessThan(versionId("1.0.0-build42")));
    }

    private fun versionId(s: String): VersionId {
        return VersionId.fromString(s)
    }
}