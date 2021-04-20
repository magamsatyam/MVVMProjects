package com.satya.mvvm.model

import com.squareup.moshi.Json

    data class VariousLongForms(
        @Json(name = "freq")
        val freq: Int,
        @Json(name = "lf")
        val lf: String,
        @Json(name = "since")
        val since: Int
    )
