package io.sn.io.sn.javelin.core.jnlp.runtime

class ApplicationInstance {

    private var stopped = false
    private lateinit var group: ThreadGroup

    /**
     * Returns the thread group.
     *
     * @return thread group of this application, unless it is stopped
     * @throws IllegalStateException if the app is not running
     */
    @Throws(IllegalStateException::class)
    fun getThreadGroup(): ThreadGroup {
        check(!stopped)
        return group
    }

}