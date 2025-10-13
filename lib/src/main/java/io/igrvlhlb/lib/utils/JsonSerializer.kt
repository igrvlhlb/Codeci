package io.igrvlhlb.lib.utils
/*
 * This file is part of the Codec(i) project
 *
 * Copyright (C) 2025 Igor A. Vilhalba
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@PublishedApi
internal object JsonSerializer {
    @OptIn(ExperimentalSerializationApi::class)
    @PublishedApi
    internal val PLAIN_JSON = Json

    @OptIn(ExperimentalSerializationApi::class)
    @PublishedApi
    internal val PLAIN_JSON_NO_NULLS = Json {
        explicitNulls = false
    }

    @OptIn(ExperimentalSerializationApi::class)
    @PublishedApi
    internal val PRETTY_JSON = Json {
        prettyPrint = true
        explicitNulls = false
        prettyPrintIndent = "\t"
    }

    @OptIn(ExperimentalSerializationApi::class)
    @PublishedApi
    internal val PRETTY_JSON_EXPLICIT_NULLS = Json {
        prettyPrint = true
        explicitNulls = true
        prettyPrintIndent = "\t"
    }

    /**
     * Serializes a value to JSON string with options for pretty printing and explicit nulls
     */
    inline fun <reified T>serialize(
        value: T,
        prettyPrint: Boolean = false,
        explicitNulls: Boolean = false
    ): String {
        return when {
            prettyPrint && explicitNulls -> PRETTY_JSON_EXPLICIT_NULLS.encodeToString(value)
            prettyPrint -> PRETTY_JSON.encodeToString(value)
            explicitNulls -> PLAIN_JSON.encodeToString(value)
            else -> PLAIN_JSON_NO_NULLS.encodeToString(value)
        }
    }
}
