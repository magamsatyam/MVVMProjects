package com.satya.mvvm.model.acronym

import com.google.gson.annotations.SerializedName


data class Vars (

	@SerializedName("lf") val lf : String,
	@SerializedName("freq") val freq : Int,
	@SerializedName("since") val since : Int
)