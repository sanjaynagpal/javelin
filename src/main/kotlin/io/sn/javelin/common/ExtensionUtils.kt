package io.sn.io.sn.javelin.common

import java.io.PrintWriter
import java.io.StringWriter

fun Throwable?.exceptionAsString() : String {
    val sw = StringWriter()
    val pw = PrintWriter(sw, true)
    this?.printStackTrace(pw)
    return sw.buffer.toString()
}