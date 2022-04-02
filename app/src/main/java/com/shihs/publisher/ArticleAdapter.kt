package com.shihs.publisher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shihs.publisher.data.Article
import com.shihs.publisher.databinding.ItemPostBinding


class ArticleAdapter : ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(DiffUtil()) {

    class ArticleViewHolder(var binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Article){
            binding.titleTv.text = item.title
            binding.contentTv.text = item.content
            binding.catagoryTv.text = item.category
            binding.createdTimetTv.text = item.createdTime.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.authorNameTv.text = item.author?.name.toString()
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem == newItem
        }

    }
}