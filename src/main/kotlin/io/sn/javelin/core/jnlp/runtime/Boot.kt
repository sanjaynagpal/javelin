package io.sn.io.sn.javelin.core.jnlp.runtime

/**
 * Main entry point for the JNLP client. It parses the command line
 * parameters and loads a JNLP file into the secure environment. This class
 * is meant to be called from the command line.
 */
class Boot {

    private val name: String = Boot::class.java.getPackage().implementationTitle
    private val version = Boot::class.java.getPackage().implementationVersion
    private val nameAndVersion = "$name $version"

    fun start() {

        // OPTION : VERBOSE
        // OPTION : HEADLESS
        // OPTION : VIEWER - Certificate viewer
        // OPTION : VERSION - Print version
        // OPTION : HELP - print help
        // OPTION : PROPERTY
        // OPTION : ABOUT
        // OPTION : ABOUT
        // OPTION : UPDATE
        // OPTION : NO_UPDATE
        // OPTION : NO_FORK
        // OPTION : TRUST_ALL
        // OPTION : TRUST_NONE
        // OPTION : NO_HEADERS
        // OPTION : REDIRECT
        // OPTION : NO_SPLASH
        // OPTION : NO_SEC - No Security Enabled
        // OPTION : OFFLINE

        JNLPRuntime.initialize()

        // OPTION : LIST_CACHE_IDS
        // OPTION : CLEAR_CACHE

    }

    fun run() {

        // Get location of JNLP file from command line arguments

        // Launch JNLP from location

    }

    fun launch(location : String) {

        // Create a Launcher

    }



}

fun main(args: Array<String>) {

}