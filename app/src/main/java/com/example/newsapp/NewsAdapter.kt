package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter( private val listener:NewsItemClicked ): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    private val items: ArrayList<News> =  ArrayList()

    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        val viewHolder = ViewHolder(v)
        v.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])

        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        val currentItem= items[position]
        holder.itemTitle.text= currentItem.title
        holder.date.text= currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemTitle : TextView = itemView.findViewById(R.id.item_title)
        val date : TextView = itemView.findViewById(R.id.author)
        val image : ImageView = itemView.findViewById(R.id.imageView)

    }
}


interface  NewsItemClicked{
    fun onItemClicked(item : News)
}