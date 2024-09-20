package io.sn.io.sn.javelin.config

import io.sn.io.sn.javelin.security.KeyStores
import java.util.*


enum class Target {
    JAVAWS, PLUGIN, ITWEB_SETTINGS, POLICY_EDITOR
}

class InfrastructureFileDescriptor(
    val filename: String,
    val pathStub: String,
    val systemPathStub: String,
    val descriptionKey: String,
    val target: Array<Target>) {


    /**
     * Returns the location of a KeyStore corresponding to the given level and type
     *
     * @param level the specified level of the key store to be returned.
     * @param type  the specified type of the key store to be returned.
     * @return the location of the key store.
     */
    fun getKeyStoreLocation(level: KeyStores.Level, type: KeyStores.Type) : InfrastructureFileDescriptor {
        when(level) {
            KeyStores.Level.SYSTEM -> {
                when(type) {
                    KeyStores.Type.JSSE_CA_CERTS -> { TODO() }
                    KeyStores.Type.CA_CERTS -> { TODO() }
                    KeyStores.Type.JSSE_CERTS -> { TODO() }
                    KeyStores.Type.CERTS -> { TODO() }
                    KeyStores.Type.CLIENT_CERTS -> { TODO() }
                }
            }
            KeyStores.Level.USER -> {
                when(type) {
                    KeyStores.Type.JSSE_CA_CERTS -> { TODO() }
                    KeyStores.Type.CA_CERTS -> { TODO() }
                    KeyStores.Type.JSSE_CERTS -> { TODO() }
                    KeyStores.Type.CERTS -> { TODO() }
                    KeyStores.Type.CLIENT_CERTS -> { TODO() }
                }
            }
        }
    }

    fun toTranslatableString(level: KeyStores.Level, type: KeyStores.Type) : String {
        val response = StringBuilder()

        response.append("KS")

        val levelString = level.toString()
        response.append(levelString.substring(0, 1).uppercase(Locale.getDefault()))
        response.append(levelString.substring(1).lowercase(Locale.getDefault()))

        val typeString = type.toString()
        val tokenizer = StringTokenizer(typeString, "_")
        while (tokenizer.hasMoreTokens()) {
            val token = tokenizer.nextToken()
            response.append(token.substring(0, 1).uppercase(Locale.getDefault()))
            response.append(token.substring(1).lowercase(Locale.getDefault()))
        }

        return response.toString()
    }
}