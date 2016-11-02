package org.sorafx

import io.undertow.Undertow
import org.sorafx.middleware.DelegateMiddleware
import org.sorafx.middleware.NoopMiddleware

/**
 * @author rabitarochan
 */
class AppBuilder() {

    private val middlewareStack = mutableListOf<MiddlewareChain>()

    fun use(middleware: Middleware): Unit {
        val chain: MiddlewareChain = DefaultMiddlewareChain(middleware)

        middlewareStack.lastOrNull()?.let {
            it.setNextChain(chain)
        }

        middlewareStack.add(chain)
    }

    fun use(block: (AppContext, MiddlewareChain?) -> Unit): Unit {
        use(DelegateMiddleware(block))
    }

    fun handle(context: AppContext): Unit {
        val rootChain = DefaultMiddlewareChain(NoopMiddleware)

        // TODO: if middlewareStack is empty, throw Exception.
        rootChain.setNextChain(middlewareStack.first())

        rootChain.next(context)
    }

}
