package com.challenge.rahatlaticisesler.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Keep all coroutines organized on this object.
 */
object CoroutineHelper {

    /**
     * Use this method when need to do an async work. Result will fill into completion block.
     *
     * @param The work that want to run asynchronously.
     * @param completion The result block that represent to work. Null if it is not necessary.
     */
    fun <T : Any> doAsyncWork(
        work: suspend (() -> T?),
        completion: ((T?) -> Unit)? = null
    ) = CoroutineScope(Dispatchers.Main).launch {
        val result = CoroutineScope(Dispatchers.IO).async {
            return@async work()
        }.await()

        completion?.let {
            it(result)
        }
    }

}