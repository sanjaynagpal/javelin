package io.sn.javelin.common.i18n

import java.text.MessageFormat
import java.util.*
import java.util.stream.Collectors


object Translator {

    private const val DEFAULT_RESOURCE_BUNDLE_BASE_NAME = "io.sn.javelin.common.i18n.Messages"
    const val MISSING_RESOURCE_PLACEHOLDER = "RNoResource"

    private val resources : ChainedResourceBundle = ChainedResourceBundle(
        bundle = ResourceBundle.getBundle(
            DEFAULT_RESOURCE_BUNDLE_BASE_NAME, Locale.getDefault()
        ),
        locale = Locale.getDefault()
    )

    private fun getBundle(fullyQualifiedBundleBaseName: String, locale: Locale) : ResourceBundle {
        val r = runCatching { ResourceBundle.getBundle(fullyQualifiedBundleBaseName, locale) }
        r.onFailure {
            throw IllegalStateException("No bundle found for locale '" + locale +
                    "' and missing base resource bundle '" + DEFAULT_RESOURCE_BUNDLE_BASE_NAME + "'.")
        }
        return r.getOrThrow()
    }

    fun addBundle(fullyQualifiedBundleBaseName: String) {
        resources.addBundle(getBundle(fullyQualifiedBundleBaseName, resources.locale))
    }

    /**
     * Return a translated (localized) version of the message
     *
     * @param message the message to translate
     * @return a string representing the localized message
     */
    fun R(message: String): String {
        return translate(message, arrayOf<Any>(0))
    }

    /**
     * @param message key to be found in properties
     * @param params  params to be expanded to message
     * @return the localized string for the message
     */
    fun R(message: String, vararg params: Any): String {
        return translate(message, params)
    }


    /**
     * @param key  key to be found in properties
     * @param args params to be expanded to message
     * @return the localized resource string using the specified arguments.
     */
    fun translate(key: String, vararg args: Any): String {
        return MessageFormat.format(translate(key), *args)
    }

    private fun translate(key: String): String {
        try {
            return resources.getString(key)
        } catch (e: NullPointerException) {
            throw IllegalArgumentException("Key '$key' to lookup resource bundle text must not be null.")
        } catch (e: MissingResourceException) {
            check(key != MISSING_RESOURCE_PLACEHOLDER) { "No missing resource placeholder key '$key' found in resource bundles." }

            // try with custom fallback placeholder that should be included in the bundle
            val languageCode = Locale.getDefault().language
            return MessageFormat.format(MISSING_RESOURCE_PLACEHOLDER, key, languageCode)
        } catch (e: ClassCastException) {
            check(key != MISSING_RESOURCE_PLACEHOLDER) { "No missing resource placeholder key '$key' found in resource bundles." }
            val languageCode = Locale.getDefault().language
            return MessageFormat.format(MISSING_RESOURCE_PLACEHOLDER, key, languageCode)
        }
    }
}

private class ChainedResourceBundle(bundle: ResourceBundle, locale: Locale = Locale.getDefault()) : ResourceBundle() {

    val bundles : MutableList<ResourceBundle> = mutableListOf(bundle)

    fun addBundle(bundle: ResourceBundle) {
        if (!bundles.contains(bundle)) bundles.add(0, bundle)
    }

    override fun getBaseBundleName() : String {
        return bundles.joinToString(separator = ",") { baseBundleName }
    }

    override fun containsKey(key: String) : Boolean {
        return bundles.any { rb -> rb.containsKey(key) }
    }

    override fun keySet() : Set<String> {
        return bundles.stream().flatMap { rb: ResourceBundle -> rb.keySet().stream() }.collect(Collectors.toSet())
    }

    override fun handleGetObject(key: String): Any? {
        for (bundle in bundles) {
            try {
                return bundle.getString(key)
            } catch (ignored: MissingResourceException) {
                // ignore and try next bundle in the list
            }
        }
        return null
    }

    override fun getKeys(): Enumeration<String> {
        val it = keySet().iterator()
        return object : Enumeration<String> {
            override fun hasMoreElements(): Boolean {
                return it.hasNext()
            }

            override fun nextElement(): String {
                return it.next()
            }
        }
    }

}