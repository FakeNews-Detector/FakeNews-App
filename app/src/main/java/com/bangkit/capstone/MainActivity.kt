package com.bangkit.capstone

import android.app.ActionBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.capstone.data.adapter.RecyclerAdapter
import com.bangkit.capstone.data.api.ApiService
import com.bangkit.capstone.data.api.NewsApiJSON
import com.bangkit.capstone.data.api.NewsApiJSONItem
import com.bangkit.capstone.databinding.ActivityMainBinding
import com.bangkit.capstone.ui.DetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_title_layout)
    }

    override fun onStart() {
        super.onStart()
        setUpRecycleView()
        getDataFromApi()
    }

    private fun setUpRecycleView() {
        recyclerAdapter = RecyclerAdapter(arrayListOf(), object : RecyclerAdapter.OnAdapterListener {
            override fun onClick(result: NewsApiJSONItem) {
                startActivity(
                    Intent(applicationContext, DetailActivity::class.java)
                    .putExtra("intent_title", result.judul)
                    .putExtra("intent_description", result.deskripsi)
                    .putExtra("intent_category", result.kategori)
                    .putExtra("intent_image", result.gambar)
                    .putExtra("intent_article", result.isiarticle))
            }
        })
        mainBinding.recycleView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = recyclerAdapter
        }
    }

    private fun getDataFromApi() {
        mainBinding.progressBar.visibility = View.VISIBLE
        ApiService.endpoint.getNews()
            .enqueue(object : Callback<NewsApiJSON> {
                override fun onFailure(call: Call<NewsApiJSON>, t: Throwable) {
                    mainBinding.progressBar.visibility = View.GONE
                    printLog("onFailure: $t")
                }
                override fun onResponse(
                    call: Call<NewsApiJSON>,
                    response: Response<NewsApiJSON>
                ) {
                    mainBinding.progressBar.visibility = View.GONE
                    if (response.isSuccessful) {
                        showNews( response.body()!! )
                    }
                }

            })
    }

    private fun printLog(message: String) {
        Log.d(TAG, message)
    }

    private fun showNews(data: NewsApiJSON) {
        recyclerAdapter.setData(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        private var TAG = "MainActivity"
    }
}