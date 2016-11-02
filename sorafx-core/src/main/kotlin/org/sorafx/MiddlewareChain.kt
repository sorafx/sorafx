package org.sorafx

/**
 * @author rabitarochan
 */
interface MiddlewareChain {

    fun setNextChain(chain: MiddlewareChain): Unit

    fun getMiddleware(): Middleware

    fun next(context: AppContext): Unit

}
