package org.sorafx

import io.undertow.server.HttpServerExchange

/**
 * @author rabitarochan
 */
data class AppContext(
        val exchange: HttpServerExchange,
        val env: MutableMap<String, Any> = mutableMapOf()
)
