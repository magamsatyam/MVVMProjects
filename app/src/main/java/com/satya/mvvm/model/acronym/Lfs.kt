package com.satya.mvvm.model.acronym

import com.google.gson.annotations.SerializedName


data class Lfs (

	@SerializedName("lf") val lf : String,
	@SerializedName("freq") val freq : Int,
	@SerializedName("since") val since : Int,
	@SerializedName("vars") val vars : List<Vars>
)