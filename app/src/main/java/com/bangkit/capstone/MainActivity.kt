package com.bangkit.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.capstone.data.adapter.RecyclerAdapter
import com.bangkit.capstone.data.api.ApiRequest
import com.bangkit.capstone.data.api.NewsApiJSON
import com.bangkit.capstone.data.api.NewsApiJSONItem
import com.bangkit.capstone.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    lateinit var countDownTimer: CountDownTimer

    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imageList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_title_layout)


        makeApiRequest()
    }

    private fun fadeInFromBlack() {
        mainBinding.blackScreen.animate().apply {
            alpha(0f)
            duration = 3000
        }.start()
    }

    private fun setUpRecyclerView() {
        mainBinding.RecycleView.layoutManager = LinearLayoutManager(applicationContext)
        mainBinding.RecycleView.adapter = RecyclerAdapter(titleList, descList, imageList)
    }

    private fun addToList(title: String, desc: String, image: String) {
        titleList.add(title)
        descList.add(desc)
        imageList.add(image)
    }

    private fun makeApiRequest() {
        mainBinding.progressBar.visibility = View.VISIBLE
        val loggingInterceptor =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = retrofit.getNews()

                for (article in response) {
                    Log.i("MainActivity", "Result = $article")
                    addToList(article.judul, article.deskripsi, article.gambar)
                }
                withContext(Dispatchers.Main) {
                    setUpRecyclerView()
                    fadeInFromBlack()
                    mainBinding.progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                Log.e("MainActivity", e.toString())

                withContext(Dispatchers.Main) {
                    attemptRequestAgain()
                }
            }
        }
    }

    private fun attemptRequestAgain() {
        countDownTimer = object : CountDownTimer(5 * 1000, 1000) {
            override fun onFinish() {
                makeApiRequest()
                countDownTimer.cancel()
            }

            override fun onTick(millisUntilFinished: Long) {
                Log.i("MainActivity", "Could not retrieve data... Trying again in ${millisUntilFinished/1000} seconds")
            }
        }
        countDownTimer.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return super.onCreateOptionsMenu(menu)
    }
}