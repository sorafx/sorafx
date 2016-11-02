package org.sorafx

/**
 * @author rabitarochan
 */
interface Bootstrapper {

    fun registerMiddleware(app: AppBuilder): Unit

}
