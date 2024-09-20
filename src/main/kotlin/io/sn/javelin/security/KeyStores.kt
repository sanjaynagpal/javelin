package io.sn.io.sn.javelin.security

object KeyStores {

    enum class Level {
        USER,
        SYSTEM
    }

    enum class Type {
        CERTS,
        JSSE_CERTS,
        CA_CERTS,
        JSSE_CA_CERTS,
        CLIENT_CERTS
    }

    private val keystorePaths : MutableMap<Int, String> = mutableMapOf()
    private const val KEYSTORE_TYPE = "JKS"

}

data class KeyStoreWithPath(val ks: KeyStore, val path: String)