package com.satya.mvvm.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class AcronymsItem(
    @Json(name = "lfs")
    val lfs: List<LongForm>,
    @Json(name = "sf")
    val sf: String
):Parcelable



