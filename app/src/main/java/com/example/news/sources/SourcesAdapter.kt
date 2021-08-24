package com.example.news.sources

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.SourcesListItemBinding
import com.example.news.model.sources.Source

class SourcesAdapter(private val clickListener: SourceClickListener): ListAdapter<Source, SourcesAdapter.SourcesViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<Source>() {
        override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
            return oldItem == newItem
        }

    }

    class SourcesViewHolder(private val binding: SourcesListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: SourceClickListener, source: Source) {
            binding.clickListener = clickListener
            binding.source = source
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesViewHolder {
        return SourcesViewHolder(SourcesListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: SourcesViewHolder, position: Int) {
        val sourceItem = getItem(position)
        holder.bind(clickListener, sourceItem)
    }

    interface SourceClickListener {
        fun onSourceClicked(source: Source)
    }

}