package com.satya.mvvm.model

import com.squareup.moshi.Json

data class AcronymsItem(
    @Json(name = "lfs")
    val lfs: List<LongForm>,
    @Json(name = "sf")
    val sf: String
)



