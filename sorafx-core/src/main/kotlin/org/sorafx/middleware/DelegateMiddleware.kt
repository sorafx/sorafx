package org.sorafx.middleware

import org.sorafx.AppContext
import org.sorafx.Middleware
import org.sorafx.MiddlewareChain

/**
 * @author rabitarochan
 */
class DelegateMiddleware(val run: (AppContext, MiddlewareChain?) -> Unit): Middleware {

    override fun handle(context: AppContext, chain: MiddlewareChain?) {
        run(context, chain)
    }

}
