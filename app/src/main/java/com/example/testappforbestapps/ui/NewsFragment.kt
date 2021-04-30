package com.example.testappforbestapps.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testappforbestapps.data.db.Entityes.NewsItem
import com.example.testappforbestapps.databinding.FragmentNewsBinding
import com.example.testappforbestapps.other.AppApplication
import com.example.testappforbestapps.other.AppViewModel
import com.example.testappforbestapps.other.AppViewModelFactory
import com.example.testappforbestapps.other.NewsAdapter
import com.squareup.kotlinpoet.COLLECTION
import java.util.*

class NewsFragment() : Fragment() {

    private lateinit var adapter: NewsAdapter
    private lateinit var binding: FragmentNewsBinding
    private val viewModel: AppViewModel by viewModels {
        AppViewModelFactory((activity?.application as AppApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = NewsAdapter(listOf())

        viewModel.allWords.observe(viewLifecycleOwner) {
            Collections.sort(it) { o1, o2 ->
                o2.publishedAt!!.compareTo(o1.publishedAt!!)
            }
            if (!viewModel.filter.value.isNullOrEmpty()) {

                var changesList: MutableList<NewsItem> = mutableListOf()
                for (i in it) {
                    if ((i.title?.toUpperCase()?.contains(
                            viewModel.filter.value!!.toUpperCase()
                        ) == true) ||
                        (i.descriptin?.toUpperCase()?.contains(
                            viewModel.filter.value!!.toUpperCase()
                        ) == true) ||
                        (i.content?.toUpperCase()?.contains(
                            viewModel.filter.value!!.toUpperCase()
                        ) == true)
                    ) {
                        changesList.add(i)
                    }
                }

                adapter.news = changesList
                adapter.notifyDataSetChanged()
            }
            else {
                adapter.news = viewModel.allWords.value!!
                adapter.notifyDataSetChanged()
            }
        }


        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter


        viewModel.filter.observe(viewLifecycleOwner){
            Collections.sort(adapter.news) { o1, o2 ->
                o2.publishedAt!!.compareTo(o1.publishedAt!!)
            }

            if (it.isNotEmpty()) {

                var changesList: MutableList<NewsItem> = mutableListOf()
                for (i in viewModel.allWords.value!!) {
                    if ((i.title?.toUpperCase()?.contains(
                            it.toUpperCase()
                        ) == true) ||
                        (i.descriptin?.toUpperCase()?.contains(
                            it.toUpperCase()
                        ) == true) ||
                        (i.content?.toUpperCase()?.contains(
                           it.toUpperCase()
                        ) == true)
                    ) {
                        changesList.add(i)
                    }
                }

                adapter.news = changesList
                adapter.notifyDataSetChanged()
            } else {
                adapter.news = viewModel.allWords.value!!
                adapter.notifyDataSetChanged()
            }

        }

        binding.materialButton.setOnClickListener {
                viewModel.filterChange(binding.enterText.text.toString())
        }

    }

}