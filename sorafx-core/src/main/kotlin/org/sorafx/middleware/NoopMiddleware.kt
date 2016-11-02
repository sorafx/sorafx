package org.sorafx.middleware

import org.sorafx.AppContext
import org.sorafx.Middleware
import org.sorafx.MiddlewareChain

/**
 * @author rabitarochan
 */
object NoopMiddleware : Middleware {

    override fun handle(context: AppContext, chain: MiddlewareChain?) {
        chain?.next(context)
    }

}
