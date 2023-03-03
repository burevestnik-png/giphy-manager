package com.example.giphyManager.common.utils

object Routes {
    const val LIST = "list"
    const val DETAILS = "details"
}

class Route(val path: String) {
    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        private val domain = "giphyApp://"
        private val queryParams: MutableMap<String, String> = mutableMapOf()

        var screen: String = ""

        fun addQueryParam(key: String, value: String) {
            queryParams[key] = value
        }

        fun build(): Route {
            val path =
                StringBuilder().apply {
                    append(domain)
                    append(screen)
                }

            if (queryParams.isNotEmpty()) {
                path.append("?")
                for ((key, value) in queryParams) {
                    if (path.endsWith("?")) {
                        path.append("$key=$value")
                    } else {
                        path.append("&$key=$value")
                    }
                }
            }

            return Route(path.toString())
        }
    }
}