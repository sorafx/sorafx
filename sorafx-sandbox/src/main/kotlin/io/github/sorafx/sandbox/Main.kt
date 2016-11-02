package io.github.sorafx.sandbox

import io.undertow.util.Headers
import org.sorafx.*

fun main(args: Array<String>) {
    val startTime = System.currentTimeMillis()
    println("Hello sorafx!")

    SorafxApplication.run(SandboxBootstrapper())

    println("Server Started [${System.currentTimeMillis() - startTime}ms]")
}

class SandboxBootstrapper() : DefaultBootstrapper() {

    override fun registerMiddleware(app: AppBuilder) {
        app.use { context, chain ->
            val startTime = System.currentTimeMillis()
            println("== Begin ==")
            println("chain-1 before")

            context.env.put("sorafx.requestPath", context.exchange.requestPath)

            chain?.next(context)
            println("chain-1 after")

            val finishTime = System.currentTimeMillis()
            println("== Finish (${context.exchange.requestMethod.toString()} ${context.exchange.requestPath}) [${finishTime - startTime}ms] ==")
        }
        (2..10).forEach { i ->
            app.use(object : Middleware {
                override fun handle(context: AppContext, chain: MiddlewareChain?) {
                    println("chain-$i before [${context.exchange.requestScheme} ${context.exchange.hostName} ${context.exchange.hostPort}]")
                    chain?.next(context)
                    println("chain-$i after")
                }
            })
        }
        app.use(object : Middleware {
            override fun handle(context: AppContext, chain: MiddlewareChain?) {
                println("application start")

                context.exchange.responseHeaders.put(Headers.CONTENT_TYPE, "text/plain")
                context.exchange.responseSender.send("Hello world [${context.env.get("sorafx.requestPath") as String}]")

                println("application finish")
            }
        })
    }

}
