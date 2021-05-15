package com.example.news.overview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.news.newsContent.NewsContentActivity
import com.example.news.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewsBinding.inflate(inflater, container, false)
        val category = arguments?.getString("category") ?: ""
        val viewModelFactory = NewsFragmentViewModelFactory(category)

        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        binding.newsRecycleView.adapter = NewsAdapter(NewsAdapter.OnClickListener {
            viewModel.displayNewsContent(it)
        })

        viewModel.navigateToSelectedNews.observe(viewLifecycleOwner, {
            if (it != null) {
                val intent = Intent(requireContext(), NewsContentActivity::class.java)
                intent.putExtra("news_content", it)
                startActivity(intent)
                viewModel.displayNewsContentComplete()
            }
        })

        return binding.root
    }

    companion object {
        fun newInstance(arg: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString("category", arg)
                }
            }
    }

}