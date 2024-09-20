package io.sn.io.sn.javelin.config

import java.nio.channels.FileLock



object ConfigurationConstants {
    const val DEPLOYMENT_CONFIG_FILE: String = "deployment.config"
    const val ITW_DEPLOYMENT_CONFIG_FILE: String = "itw-deployment.config"

    const val DEPLOYMENT_PROPERTIES: String = "deployment.properties"

    const val APPLET_TRUST_SETTINGS: String = ".appletTrustSettings"

    const val DEPLOYMENT_COMMENT: String = "Netx deployment configuration"

    const val JNLP_ASSOCIATION_NEVER: Int = 0

    const val JNLP_ASSOCIATION_ASK_USER: Int = 2

    const val JNLP_ASSOCIATION_REPLACE_ASK: Int = 3

    /**
     * when set to as value of KEY_CONSOLE_STARTUP_MODE = "deployment.console.startup.mode",
     * then console is not visible by default, but may be shown
     */
    const val CONSOLE_HIDE: String = "HIDE"

    /**
     * when set to as value of KEY_CONSOLE_STARTUP_MODE = "deployment.console.startup.mode",
     * then console show for both javaws and plugin
     */
    const val CONSOLE_SHOW: String = "SHOW"

    /**
     * when set to as value of KEY_CONSOLE_STARTUP_MODE = "deployment.console.startup.mode",
     * then console is not visible by default, nop data are passed to it (save memory and cpu) but can not be shown
     */
    const val CONSOLE_DISABLE: String = "DISABLE"

    /**
     * when set to as value of KEY_CONSOLE_STARTUP_MODE = "deployment.console.startup.mode",
     * then console show for  plugin
     */
    const val CONSOLE_SHOW_PLUGIN: String = "SHOW_PLUGIN_ONLY"

    /**
     * when set to as value of KEY_CONSOLE_STARTUP_MODE = "deployment.console.startup.mode",
     * then console show for javaws
     */
    const val CONSOLE_SHOW_JAVAWS: String = "SHOW_JAVAWS_ONLY"

    const val KEY_USER_CACHE_DIR: String = "deployment.user.cachedir"

    const val KEY_USER_PERSISTENCE_CACHE_DIR: String = "deployment.user.pcachedir"

    const val KEY_SYSTEM_CACHE_DIR: String = "deployment.system.cachedir"

    const val KEY_CACHE_MAX_SIZE: String = "deployment.cache.max.size"

    const val KEY_CACHE_ENABLED: String = "deployment.javapi.cache.enabled"

    const val KEY_CACHE_COMPRESSION_ENABLED: String = "deployment.cache.jarcompression"

    const val KEY_USER_LOG_DIR: String = "deployment.user.logdir"

    const val KEY_USER_TMP_DIR: String = "deployment.user.tmp"

    /**
     * the directory containing locks for single instance applications
     */
    const val KEY_USER_LOCKS_DIR: String = "deployment.user.locksdir"

    const val KEY_USER_NETX_RUNNING_FILE: String = "deployment.user.runningfile"

    const val KEY_USER_SECURITY_POLICY: String = "deployment.user.security.policy"

    const val KEY_USER_SECURITY_POLICY_CHECK: String = "deployment.user.security.policy.check"

    const val KEY_USER_TRUSTED_CA_CERTS: String = "deployment.user.security.trusted.cacerts"

    const val KEY_USER_TRUSTED_JSSE_CA_CERTS: String = "deployment.user.security.trusted.jssecacerts"

    const val KEY_USER_TRUSTED_CERTS: String = "deployment.user.security.trusted.certs"

    const val KEY_USER_TRUSTED_JSSE_CERTS: String = "deployment.user.security.trusted.jssecerts"

    const val KEY_USER_TRUSTED_CLIENT_CERTS: String = "deployment.user.security.trusted.clientauthcerts"

    const val KEY_SYSTEM_SECURITY_POLICY: String = "deployment.system.security.policy"

    const val KEY_SYSTEM_TRUSTED_CA_CERTS: String = "deployment.system.security.cacerts"

    const val KEY_SYSTEM_TRUSTED_JSSE_CA_CERTS: String = "deployment.system.security.jssecacerts"

    const val KEY_SYSTEM_TRUSTED_CERTS: String = "deployment.system.security.trusted.certs"

    const val KEY_SYSTEM_TRUSTED_JSSE_CERTS: String = "deployment.system.security.trusted.jssecerts"

    const val KEY_SYSTEM_TRUSTED_CLIENT_CERTS: String = "deployment.system.security.trusted.clientautcerts"

    const val KEY_SECURITY_ASKGRANTDIALOG_NOTINCA: String = "deployment.security.askgrantdialog.notinca"

    const val KEY_SECURITY_NOTINCA_WARNING: String = "deployment.security.notinca.warning"

    const val KEY_SECURITY_EXPIRED_WARNING: String = "deployment.security.expired.warning"

    const val KEY_SECURITY_JSSE_HOSTMISMATCH_WARNING: String = "deployment.security.jsse.hostmismatch.warning"

    /**
     * Boolean. Only show security prompts to user if true
     */
    const val KEY_SECURITY_PROMPT_USER: String = "deployment.security.askgrantdialog.show"

    //enum of AppletSecurityLevel in result
    const val KEY_SECURITY_LEVEL: String = "deployment.security.level"

    const val KEY_SECURITY_TRUSTED_POLICY: String = "deployment.security.trusted.policy"

    /**
     * Boolean. Only give AWTPermission("showWindowWithoutWarningBanner") if true
     */
    const val KEY_SECURITY_ALLOW_HIDE_WINDOW_WARNING: String = "deployment.security.sandbox.awtwarningwindow"

    /**
     * Boolean. Only prompt user for granting any JNLP permissions if true
     */
    const val KEY_SECURITY_PROMPT_USER_FOR_JNLP: String = "deployment.security.sandbox.jnlp.enhanced"

    const val KEY_PARALLEL_RESOURCE_DOWNLOAD_COUNT: String = "deployment.cache.parallelDownloadCount"
    const val DEFAULT_PARALLEL_RESOURCE_DOWNLOAD_COUNT: Int = 6

    /**
     * Boolean. Only install the custom authenticator if true
     */
    const val KEY_SECURITY_ITW_IGNORECERTISSUES: String = "deployment.security.itw.ignorecertissues"

    /**
     * Boolean. Create regular files instead of restricted files if true.
     */
    const val KEY_SECURITY_DISABLE_RESTRICTED_FILES: String = "deployment.security.itw.disablerestrictedfiles"

    const val KEY_STRICT_JNLP_CLASSLOADER: String = "deployment.jnlpclassloader.strict"

    /**
     * Boolean. Do not prefer https over http
     */
    const val KEY_HTTPS_DONT_ENFORCE: String = "deployment.https.noenforce"

    /**
     * the proxy type. possible values are `JNLPProxySelector.PROXY_TYPE_*`
     */
    const val KEY_PROXY_TYPE: String = "deployment.proxy.type"

    /**
     * Boolean. If true, the http host/port should be used for https and ftp as well
     */
    const val KEY_PROXY_SAME: String = "deployment.proxy.same"

    const val KEY_PROXY_AUTO_CONFIG_URL: String = "deployment.proxy.auto.config.url"

    const val KEY_PROXY_BYPASS_LIST: String = "deployment.proxy.bypass.list"

    const val KEY_PROXY_BYPASS_LOCAL: String = "deployment.proxy.bypass.local"

    const val KEY_PROXY_HTTP_HOST: String = "deployment.proxy.http.host"

    const val KEY_PROXY_HTTP_PORT: String = "deployment.proxy.http.port"

    const val KEY_PROXY_HTTPS_HOST: String = "deployment.proxy.https.host"

    const val KEY_PROXY_HTTPS_PORT: String = "deployment.proxy.https.port"

    const val KEY_PROXY_FTP_HOST: String = "deployment.proxy.ftp.host"

    const val KEY_PROXY_FTP_PORT: String = "deployment.proxy.ftp.port"

    const val KEY_PROXY_SOCKS4_HOST: String = "deployment.proxy.socks.host"

    const val KEY_PROXY_SOCKS4_PORT: String = "deployment.proxy.socks.port"

    const val KEY_PROXY_OVERRIDE_HOSTS: String = "deployment.proxy.override.hosts"

    /*
     * Logging
     */
    const val KEY_ENABLE_DEBUG_LOGGING: String = "deployment.log" //same as verbose or ICEDTEAPLUGIN_DEBUG=true

    const val KEY_ENABLE_LOGGING_HEADERS: String = "deployment.log.headers" //will add header OutputContorll.getHeader To all messages

    const val KEY_ENABLE_LOGGING_OF_JNLP_FILE_CONTENT: String = "deployment.log.jnlpFileContent"

    const val KEY_ENABLE_LOGGING_TOFILE: String = "deployment.log.file"

    const val KEY_ENABLE_APPLICATION_LOGGING_TOFILE: String = "deployment.log.file.clientapp" //also client app will log to its separate file

    const val KEY_ENABLE_LEGACY_LOGBASEDFILELOG: String = "deployment.log.file.legacylog"

    const val KEY_ENABLE_LOGGING_TOSTREAMS: String = "deployment.log.stdstreams"

    const val KEY_ENABLE_LOGGING_TOSYSTEMLOG: String = "deployment.log.system"

    /*
     * manifest check
     */
    const val KEY_ENABLE_MANIFEST_ATTRIBUTES_CHECK: String = "deployment.manifest.attributes.check"

    const val KEY_ASSUME_FILE_STEM_IN_CODEBASE: String = "deployment.assumeFileSystemInCodebase"

    /**
     * Console initial status.
     * One of CONSOLE_* values
     * See declaration above:
     * CONSOLE_HIDE = "HIDE";
     * CONSOLE_SHOW = "SHOW";
     * CONSOLE_DISABLE = "DISABLE";
     * CONSOLE_SHOW_PLUGIN = "SHOW_PLUGIN_ONLY";
     * CONSOLE_SHOW_JAVAWS = "SHOW_JAVAWS_ONLY";
     */
    const val KEY_CONSOLE_STARTUP_MODE: String = "deployment.console.startup.mode"

    const val KEY_JNLP_ASSOCIATIONS: String = "deployment.javaws.associations"

    const val KEY_CREATE_DESKTOP_SHORTCUT: String = "deployment.javaws.shortcut"

    const val KEY_JRE_INTSTALL_URL: String = "deployment.javaws.installURL"

    const val KEY_AUTO_DOWNLOAD_JRE: String = "deployment.javaws.autodownload"

    const val KEY_BROWSER_PATH: String = "deployment.browser.path"

    //for legacy reasons, also $BROWSER variable is supported
    const val BROWSER_ENV_VAR: String = "BROWSER"

    // both browser.path and BROWSER can ave those for-fun keys:
    const val ALWAYS_ASK: String = "ALWAYS-ASK"

    const val INTERNAL_HTML: String = "INTERNAL-HTML"

    const val LEGACY_WIN32_URL__HANDLER: String = "rundll32 url.dll,FileProtocolHandler "

    const val KEY_UPDATE_TIMEOUT: String = "deployment.javaws.update.timeout"

    const val IGNORE_HEADLESS_CHECK: String = "deployment.headless.ignore"

    /*
     * JVM arguments for plugin
     */
    const val KEY_PLUGIN_JVM_ARGUMENTS: String = "deployment.plugin.jvm.arguments"

    const val KEY_JRE_DIR: String = "deployment.jre.dir"

    /**
     * remote configuration properties
     */
    const val KEY_SYSTEM_CONFIG: String = "deployment.system.config"

    const val KEY_SYSTEM_CONFIG_MANDATORY: String = "deployment.system.config.mandatory"

    /**
     * Possibility to control hack which resizes very small applets
     */
    const val KEY_SMALL_SIZE_OVERRIDE_THRESHOLD: String = "deployment.small.size.threshold"

    const val KEY_SMALL_SIZE_OVERRIDE_WIDTH: String = "deployment.small.size.override.width"

    const val KEY_SMALL_SIZE_OVERRIDE_HEIGHT: String = "deployment.small.size.override.height"

    const val VV_POSSIBLE_BROWSER_VALUES: String = "VVPossibleBrowserValues"
    const val ICEDTEA_SO: String = "IcedTeaPlugin.so"
    const val CACHE_INDEX_FILE_NAME: String = "recently_used.cache"
    const val OLD_CACHE_INDEX_FILE_NAME: String = "recently_used"
    const val WINDIR: String = "WINDIR"
    const val SECURITY_WORD: String = "security"
    const val DEPLOYMENT_SUBDIR_DIR: String = "icedtea-web"
    const val XDG_CONFIG_HOME_VAR: String = "XDG_CONFIG_HOME"
    const val XDG_CACHE_HOME_VAR: String = "XDG_CACHE_HOME"
    const val XDG_RUNTIME_DIR_VAR: String = "XDG_RUNTIME_DIR"
    const val XDG_DATA_HOME_VAR: String = "XDG_DATA_HOME"
    const val WIN_VARIABLE_PREFIX: String = "%"
    const val UNIX_VARIABLE_PREFIX: String = "$"

    /*
     * Native (rust)
     */
    const val KEY_LAUNCHER_RUST_CP_ADD: String = "deployment.launcher.rust.cp.add"
    const val KEY_LAUNCHER_RUST_CP_REMOVE: String = "deployment.launcher.rust.cp.remove"
    const val KEY_LAUNCHER_RUST_BOOTCP_ADD: String = "deployment.launcher.rust.bootcp.add"
    const val KEY_LAUNCHER_RUST_BOOTCP_REMOVE: String = "deployment.launcher.rust.bootcp.remove"

    /*
     * CSV
     */
    const val KEY_SECURITY_SERVER_WHITELIST: String = "deployment.security.whitelist"
    const val KEY_JVM_ARGS_WHITELIST: String = "deployment.jvm.arguments.whitelist"

    /*
     * HTTP Connection properties
     */
    const val KEY_HTTPCONNECTION_CONNECT_TIMEOUT: String = "deployment.connection.connectTimeout"
    const val KEY_HTTPCONNECTION_READ_TIMEOUT: String = "deployment.connection.readTimeout"
    const val KEY_HTTPCONNECTION_REQUEST_INTERVAL: String = "deployment.connection.request.interval"
}