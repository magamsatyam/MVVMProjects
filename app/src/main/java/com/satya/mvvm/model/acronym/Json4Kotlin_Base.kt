package com.satya.mvvm.model.acronym

import com.google.gson.annotations.SerializedName



data class AcronymItem(

	@SerializedName("sf") val sf: String,
	@SerializedName("lfs") val lfs: List<Lfs>
)