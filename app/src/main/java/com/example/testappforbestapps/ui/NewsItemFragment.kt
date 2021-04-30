package com.example.testappforbestapps.ui

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.testappforbestapps.data.db.Entityes.NewsItem
import com.example.testappforbestapps.databinding.FragmentNewsItemBinding
import com.example.testappforbestapps.other.AppApplication
import com.example.testappforbestapps.other.AppViewModel
import com.example.testappforbestapps.other.AppViewModelFactory

class NewsItemFragment() : Fragment() {


    private val viewModel: AppViewModel by viewModels {
        AppViewModelFactory((activity?.application as AppApplication).repository)
    }
    private val args: NewsItemFragmentArgs by navArgs()

    private lateinit var binding: FragmentNewsItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewsItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsItem = args.item

        val listNews = viewModel.allWords.value
        binding.textTitle.text = newsItem.title
        binding.textViewContent.text = newsItem.content
        binding.textViewTime.text = newsItem.publishedAt
        try{
            val bmp = BitmapFactory.decodeByteArray(newsItem.image, 0, newsItem.image!!.size)
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
}