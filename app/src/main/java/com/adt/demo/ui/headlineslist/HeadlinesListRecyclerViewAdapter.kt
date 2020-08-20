package com.adt.demo.ui.headlineslist

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.adt.demo.R
import com.adt.demo.data.model.Article
import com.adt.demo.databinding.HeadlineListItemBinding


class HeadlinesListRecyclerViewAdapter: RecyclerView.Adapter<HeadlinesListRecyclerViewAdapter.ViewHolder>() {

    private val articleList = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<HeadlineListItemBinding>(
            inflater, R.layout.headline_list_item, parent, false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount() = articleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList[position]
        holder.bind(article)
    }

    fun updateList(updates: List<Article>) {
        articleList.clear()
        articleList.addAll(updates)
        notifyDataSetChanged()
    }

    //Uses data binding for setting the view
    inner class ViewHolder(private val binding: HeadlineListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            binding.row.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(article.url)
                startActivity(it.context, intent, null)
            }
            binding.executePendingBindings()
        }
    }
}