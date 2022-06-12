package com.bangkit.capstone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.bangkit.capstone.R
import com.bangkit.capstone.data.api.ApiService
import com.bangkit.capstone.data.api.PredictData
import com.bangkit.capstone.data.api.PredictResponses
import com.bangkit.capstone.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = intent.getStringExtra("intent_category")

        detailBinding.tvTitle.text = intent.getStringExtra("intent_title")
        detailBinding.tvDescription.text = intent.getStringExtra("intent_description")
        detailBinding.tvCategory.text = "${getString(R.string.tv_kategori_berita)} ${intent.getStringExtra("intent_category")}"
        detailBinding.article.text = intent.getStringExtra("intent_article")
        Glide.with(this)
            .load(intent.getStringExtra("intent_image"))
            .into(detailBinding.imageView)

//        sendPredict(title = NewsApiJSONItem)

        sendPredict()
    }

    private fun sendPredict(title: String? = intent.getStringExtra("intent_title")) {
        val predictData = PredictData(title.toString())
        ApiService.endpoint.sendPredict(predictData)
            .enqueue(object : Callback<PredictResponses> {
                override fun onFailure(call: Call<PredictResponses>, t: Throwable) {
                    Log.d(PREDICT, "$t")
                    Log.d(TITLE, "$t")
                }

                override fun onResponse(
                    call: Call<PredictResponses>,
                    response: Response<PredictResponses>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        detailBinding.btnPredict.setOnClickListener {
                            Toast.makeText(
                                this@DetailActivity,
                                "Berita ini terindikasi : ${body?.textLabel}",
                                Toast.LENGTH_LONG).show()
                        }
                    }
                }

            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        private var PREDICT = "predict"
        private var TITLE = "title"
    }
}