package com.bangkit.capstone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.bangkit.capstone.R
import com.bangkit.capstone.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share, menu)
        return super.onCreateOptionsMenu(menu)
    }
}