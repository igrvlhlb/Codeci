package io.igrvlhlb.lib.utils

import android.os.Build
import android.util.Log
import androidx.annotation.ChecksSdkIntAtLeast

@ChecksSdkIntAtLeast(parameter = 0)
fun sdkAtLeast(version: Int): Boolean {
    return Build.VERSION.SDK_INT >= version
}

fun <T : Any?> fragile(block: () -> T): T? = try {
    block()
} catch (e: Exception) {
    Log.w("FragileCall", "Caught exception in fragile call\n" +
            Log.getStackTraceString(e)
    )
    null
}