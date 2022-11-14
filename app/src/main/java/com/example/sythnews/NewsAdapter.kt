package com.example.sythnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sythnews.NewsAdapter.MyViewHolder

class NewsAdapter(private val dataNews: List<ArticlesItem?>?) : RecyclerView.Adapter<MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvJudulBerita: TextView = itemView.findViewById(R.id.tv_judul_berita)
        private var tvDeskripsiBerita: TextView = itemView.findViewById(R.id.tv_deskripsi_berita)

        fun bind(articlesItem: ArticlesItem) {
            tvJudulBerita.text = articlesItem.title
            tvDeskripsiBerita.text = articlesItem.description
        }
    }

    private var news = listOf<ArticlesItem>()

    fun setNews(news: List<ArticlesItem>) {
        this.news = news
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_berita, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

}