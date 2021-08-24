package com.example.news.sources

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.news.R
import com.example.news.databinding.FragmentSourcesBinding
import com.example.news.model.sources.Source
import com.example.news.overview.NewsFragment

class SourcesFragment : Fragment(), SourcesAdapter.SourceClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSourcesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val viewModel: SourcesFragmentViewModel by viewModels()
        binding.viewModel = viewModel

        val recyclerView = binding.sourceRecyclerView
        recyclerView.adapter = SourcesAdapter(this)

        return binding.root
    }

    override fun onSourceClicked(source: Source) {
        val bundle = Bundle().apply {
            putString("source", source.id)
            putString("sourceName", source.name)
        }
        findNavController().navigate(R.id.action_sourcesFragment_to_newsFragment, bundle)
    }

}