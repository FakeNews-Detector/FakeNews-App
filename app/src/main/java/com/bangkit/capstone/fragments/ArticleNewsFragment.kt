package com.bangkit.capstone.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.bangkit.capstone.R


class ArticleNewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_news, container, false)
    }
}