package com.bangkit.capstone.data.api
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class NewsApiJSON : ArrayList<NewsApiJSONItem>()

@Parcelize
data class NewsApiJSONItem(
    val deskripsi: String,
    val gambar: String,
    val idarticle: Int,
    val isiarticle: String,
    val judul: String,
    val kategori: String
): Parcelable

data class PredictData (
    val text: String
)

@Parcelize
data class PredictResponses(
//    @SerializedName("avg_pred")
//    val avgPred: String,
    @SerializedName("text_label")
    val textLabel: String
): Parcelable