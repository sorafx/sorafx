package org.sorafx

/**
 * @author rabitarochan
 */
interface Middleware {

    fun handle(context: AppContext, chain: MiddlewareChain?): Unit

}
