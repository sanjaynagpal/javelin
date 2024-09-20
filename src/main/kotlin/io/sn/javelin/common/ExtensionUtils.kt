package io.sn.io.sn.javelin.common

import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.net.URL
import kotlin.jvm.Throws

/**
 * Throwable Extensions
 */
fun Throwable?.exceptionAsString() : String {
    val sw = StringWriter()
    val pw = PrintWriter(sw, true)
    this?.printStackTrace(pw)
    return sw.buffer.toString()
}

/**
 * String Extensions
 */
fun String.isNumeric() : Boolean {
    return toIntOrNull() != null
}

fun String.asciiSum() : Int {
    return sumOf { it.code }
}

@Throws(Exception::class)
fun String.toURL() : URL {
    val localFile = File(this)
    runCatching {  }
    TODO()
}

/**
 * Array Extensions
 */
fun Array<String>.asciiSum() : Int {
    return sumOf { x -> x.asciiSum() }
}

fun Array<String>.asciiSum(maxIndex: Int) : Int {
    val rangeIndex = if (maxIndex <= size) maxIndex else size
    return take(rangeIndex).sumOf { x -> x.asciiSum() }
}

fun Array<String>.isLessThan(other: Array<String>) : Boolean {
    return this.asciiSum() < other.asciiSum()
}

fun Array<String>.expandAndFill(newSize: Int, fill: String = "0") : Array<String> {
    val newArray = this.copyOf(newSize)
    for (i in size until newSize) {
        newArray[i] = fill
    }
    return newArray.requireNoNulls()
}