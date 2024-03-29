package com.example.news

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.news.model.category.Article
import com.example.news.model.sources.Source
import com.example.news.overview.NewsAdapter
import com.example.news.sources.SourcesAdapter
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("newsListData")
fun bindNewsDataToRecyclerView(recyclerView: RecyclerView, data: List<Article>?) {
    val adapter = recyclerView.adapter as NewsAdapter
    adapter.submitList(data)
}

@BindingAdapter("sourceListData")
fun bindSourceDataToRecyclerView(recyclerView: RecyclerView, data: List<Source>?) {
    val adapter = recyclerView.adapter as SourcesAdapter
    adapter.submitList(data)
}

@BindingAdapter("setDateAndTime")
fun setDateAndTime(textview: TextView, dateAndTime: String?) {
    if (dateAndTime != null) {
        val dateAndTime = dateAndTime.substring(0..18)
        val orgFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.UK)
        val targetFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.UK)
        val date = orgFormat.parse(dateAndTime)
        val newDate = targetFormat.format(date!!)
        textview.text = newDate
    }
}


@BindingAdapter("setTitle")
fun setNewsTitle(textview: TextView, title: String?) {
    if (title != null) {
        textview.text = title
    } else {
        textview.text = ""
    }
}

@BindingAdapter("setDescription")
fun setNewsDescription(textview: TextView, description: String?) {
    if (description != null) {
        textview.text = description
    } else {
        textview.text = ""
    }
}

@BindingAdapter("setContent")
fun setNewsContent(textview: TextView, content: String?) {
    if (content != null) {
        textview.text = content
    } else {
        textview.text = ""
    }
}

@BindingAdapter("setImage")
fun setNewsImage(imageView: ImageView, imageUrl: String?) {
    if(imageUrl != null) {
        val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_img)
                    .error(R.drawable.ic_baseline_broken_image_24)
            )
            .centerCrop()
            .into(imageView)
        imageView.visibility = View.VISIBLE
    } else {
        imageView.visibility = View.GONE
    }
}

@BindingAdapter("setNewsUrl")
fun setNewsUrl(textview: TextView, url: String?) {
    val htmlWeb = "<a href='$url'>Read More...</a>"
    textview.text = HtmlCompat.fromHtml(htmlWeb, HtmlCompat.FROM_HTML_MODE_COMPACT)
}


enum class NetworkRequestStatus{LOADING, ERROR, DONE}
@BindingAdapter("setStatusProgress")
fun setStatusProgress(progressBar: ProgressBar, status: NetworkRequestStatus?) {
    when(status) {
        NetworkRequestStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
//            imageView.setImageResource(R.drawable.loading_animation)
        }
        NetworkRequestStatus.ERROR -> {
            progressBar.visibility = View.VISIBLE
//            imageView.setImageResource(R.drawable.ic_baseline_cloud_off_24)
        }
        NetworkRequestStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
    }
}