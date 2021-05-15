package com.example.news

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.news.overview.NewsFragment


class PageAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    private val categories = mutableListOf("technology", "business", "health", "science", "sports", "entertainment")

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun createFragment(position: Int): Fragment {
        return NewsFragment.newInstance(categories[position])
    }
}