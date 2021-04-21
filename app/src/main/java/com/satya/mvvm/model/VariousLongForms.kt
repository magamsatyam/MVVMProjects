package com.satya.mvvm.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
    data class VariousLongForms(
        @Json(name = "freq")
        val freq: Int,
        @Json(name = "lf")
        val lf: String,
        @Json(name = "since")
        val since: Int
    ):Parcelable
