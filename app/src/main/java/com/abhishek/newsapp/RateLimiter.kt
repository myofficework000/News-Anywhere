package com.abhishek.newsapp

import android.os.SystemClock
import android.support.v4.util.ArrayMap
import java.util.concurrent.TimeUnit


/**
 * Created by abhishek.pathak on 08/04/2020.
 */
class RateLimiter<KEY>(timeout: Int, timeUnit: TimeUnit) {
    private val timestamps = ArrayMap<KEY, Long>()
    private val timeout: Long = timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun shouldFetch(key: KEY): Boolean {
        val lastFetched = timestamps[key]
        val now = now()
        if (lastFetched == null) {
            timestamps.put(key, now)
            return true
        }
        if (now - lastFetched > timeout) {
            timestamps.put(key, now)
            return true
        }
        return false
    }

    private fun now(): Long {
        return SystemClock.uptimeMillis()
    }

    @Synchronized
    fun reset(key: KEY) {
        timestamps.remove(key)
    }
}