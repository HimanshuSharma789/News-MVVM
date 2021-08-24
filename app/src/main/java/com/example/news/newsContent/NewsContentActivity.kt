package com.example.news.newsContent

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.news.R
import com.example.news.databinding.ActivityNewsContentBinding
import com.example.news.model.category.Article

class NewsContentActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsContentViewModel
    private lateinit var binding: ActivityNewsContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_content)
        binding.lifecycleOwner = this

        val article = intent.getSerializableExtra("news_content") as Article

        val viewModelFactory = NewsContentViewModelFactory(article, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsContentViewModel::class.java)
        binding.viewModel = viewModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.readMoreTextView.isClickable = true
        binding.readMoreTextView.movementMethod = LinkMovementMethod.getInstance()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(android.R.id.home == item.itemId) {
            finish()
        }
        return true
    }
}