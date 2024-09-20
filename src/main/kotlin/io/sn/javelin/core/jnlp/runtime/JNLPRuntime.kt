package io.sn.io.sn.javelin.core.jnlp.runtime

import javax.net.ssl.TrustManager

/**
 * <p>
 * Configure and access the runtime environment. This class stores global jnlp properties
 * such as default download indicators, the "install" or "base" directory, the default
 * resource update policy, etc. Some properties cannot be changed after initialized
 * </p>
 * <p>
 * The JNLP runtime can be locked to prevent further changes in runtime environment
 * except by specified class.If set, only instances of the <i>exit class</i> can exit the JVM or
 * change the JNLP runtime settings once the runtime has been initialized.
 * </p>
 */

object JNLPRuntime {

    /**
     * Initialize the JNLP runtime environment by installing the security manager and security
     * policy, initializing the JNLP standard services, etc.
     */
    fun initialize() {
    }

    private fun getSSLSocketTrustManager() : TrustManager {
        TODO()
    }
}