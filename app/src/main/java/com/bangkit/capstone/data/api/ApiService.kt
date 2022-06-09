package com.bangkit.capstone.data.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiService(

	@field:SerializedName("ApiConfig")
	val apiConfig: List<ApiConfigItem?>? = null
) : Parcelable

@Parcelize
data class ApiConfigItem(

	@field:SerializedName("idarticle")
	val idarticle: Int? = null,

	@field:SerializedName("isiarticle")
	val isiarticle: String? = null,

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null
) : Parcelable
