package com.example.testappforbestapps.other

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.testappforbestapps.data.db.Entityes.NewsItem
import com.example.testappforbestapps.databinding.NewsInRvBinding
import com.example.testappforbestapps.ui.NewsFragmentDirections


class NewsAdapter(
    var news: List<NewsItem>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(
        private var binding: NewsInRvBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(news: NewsItem) {
            binding.title.text = news.title.toString()
            binding.description.text = news.descriptin.toString()
            binding.timeData.text = news.publishedAt.toString()

            try{
                val bmp = BitmapFactory.decodeByteArray(news.image, 0, news.image!!.size)
                val drawable: Drawable = BitmapDrawable(bmp)
                binding.imageView.setImageDrawable(drawable)
                binding.imageView.maxHeight = 400
                binding.imageView.maxWidth = 400
                binding.imageView.minimumHeight= 380
                binding.imageView.minimumWidth = 380
            }catch (e: NullPointerException){
                e.printStackTrace()
            }


        }

        constructor(
            parent: ViewGroup
        ) : this(
            NewsInRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        init {

            binding.root.setOnClickListener {
                val action = NewsFragmentDirections.navigateToItem(news[adapterPosition])
                Navigation.findNavController(binding.root).navigate(action)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val curItem = news[position]
        holder.bind(curItem)
    }

    override fun getItemCount(): Int {
        return news.size
    }
}