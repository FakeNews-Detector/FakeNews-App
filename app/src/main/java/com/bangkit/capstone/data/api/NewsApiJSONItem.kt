package com.bangkit.capstone.data.api
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class NewsApiJSONItem(
    val deskripsi: String,
    val gambar: String,
    val idarticle: Int,
    val isiarticle: String,
    val judul: String,
    val kategori: String
): Parcelable