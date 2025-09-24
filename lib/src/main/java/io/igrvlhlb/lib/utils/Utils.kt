package io.igrvlhlb.lib.utils

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

@ChecksSdkIntAtLeast(parameter = 0)
fun sdkAtLeast(version: Int): Boolean {
    return Build.VERSION.SDK_INT >= version
}