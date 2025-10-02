package io.igrvlhlb.lib.utils

import android.os.Build
import android.util.Log
import androidx.annotation.ChecksSdkIntAtLeast

@ChecksSdkIntAtLeast(parameter = 0)
fun sdkAtLeast(version: Int): Boolean {
    return Build.VERSION.SDK_INT >= version
}

@ChecksSdkIntAtLeast(parameter = 0, lambda = 1)
fun <T: Any>sdkAtLeastOrNull(version: Int, block: () -> T?): T? {
    return (if (sdkAtLeast(version)) block() else null)
}

fun <T : Any?> fragile(block: () -> T): T? = try {
    block()
} catch (e: Exception) {
    Log.w("FragileCall", "Caught exception in fragile call\n" +
            Log.getStackTraceString(e)
    )
    null
}