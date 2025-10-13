package io.igrvlhlb.lib.utils
/*
 * This file is part of the Codec(i) project
 *
 * Copyright (C) 2025 Igor A. Vilhalba
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

fun Any.setPrivateField(
    fieldName: String,
    value: Any?,
    klass: Class<out Any> = this::class.java
) {
    val field = klass.getDeclaredField(fieldName)
    field.isAccessible = true
    field.set(this, value)
}

@Suppress("UNCHECKED_CAST")
fun <T> Any.getPrivateField(
    fieldName: String,
    klass: Class<out Any> = this::class.java
): T {
    val field = klass.getDeclaredField(fieldName)
    field.isAccessible = true
    return field.get(this) as T
}
