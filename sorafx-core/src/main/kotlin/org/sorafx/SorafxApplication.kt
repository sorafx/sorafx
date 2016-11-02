package org.sorafx

import io.undertow.Undertow

/**
 * @author rabitarochan
 */
object SorafxApplication {

    fun run(bootstrapper: Bootstrapper): Unit {
        val app = AppBuilder()

        bootstrapper.registerMiddleware(app)

        val undertow = Undertow.builder().apply {
            addHttpListener(8080, "localhost")
            setHandler { exchange ->
                val context = AppContext(exchange)
                app.handle(context)
            }
        }.build()

        undertow.start()
    }

}
