package org.sorafx

/**
 * @author
 */
class DefaultMiddlewareChain(private val middleware: Middleware) : MiddlewareChain {

    private var nextChain: MiddlewareChain? = null

    override fun setNextChain(chain: MiddlewareChain) {
        nextChain = chain
    }

    override fun getMiddleware(): Middleware {
        return middleware
    }

    override fun next(context: AppContext) {
        middleware.handle(context, nextChain)
    }

}
