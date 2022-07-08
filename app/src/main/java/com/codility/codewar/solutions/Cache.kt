package com.codility.codewar.solutions

fun main(args: Array<String>) {
    cacheTest()
}

fun cacheTest() {
    val cache = object {
        private val cacheClass = mutableListOf<CacheClass>()

        fun add(key: String, value: String): String = if (cacheClass.any { it.key == key }) {
            cacheClass.find { it.key == key }.also { it?.value = value }
            "overwritten"
        } else {
            cacheClass.add(CacheClass(key = key, value = value))
            "added"
        }

        fun get(key: String): String = cacheClass.find { it.key == key }?.value ?: "miss"

        fun size(): Int = cacheClass.size
    }

    println(cache.add("article-123", "https://coderbyte.com/article-123"))
    println(cache.add("article-456", "https://coderbyte.com/article-456"))
    println(cache.add("how-to-code-444", "https://coderbyte.com/how-to-code-444"))
    println(cache.get("first-article"))
    println(cache.get("second-article"))
    println(cache.get("article-456"))
    println(cache.add("article-123", "https://coderbyte.com/article-123"))
    println(cache.size())
}

data class CacheClass(
    var key: String,
    var value: String
)
