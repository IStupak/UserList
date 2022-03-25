package com.example.domain.utils

internal suspend fun <T> loadAndCache(
    loadFromNetwork: suspend () -> List<T>,
    loadFromDatabase: suspend () -> List<T>,
    updateItemsInDatabase: suspend (List<T>) -> Unit
): ResultOf<List<T>> {
    return try {
        val items = loadFromNetwork()
        updateItemsInDatabase(items)
        ResultOf.Success(items)
    } catch (e: Exception) {
        val itemsFromDatabase = loadFromDatabase()
        if (itemsFromDatabase.isEmpty()) {
            ResultOf.Failure(message = null, throwable = e)
        } else {
            ResultOf.Success(itemsFromDatabase, fromCache = true)
        }
    }
}