package com.example.fakenews2.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fakenews2.R
import com.example.fakenews2.databinding.FragmentNewsBinding
import com.example.fakenews2.presentation.models.News
import com.example.fakenews2.presentation.newsrecycler.NewsAdapter
import com.example.fakenews2.presentation.newsrecycler.OnNewsClickListener
import com.example.fakenews2.presentation.viewmodels.NewsFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val newsViewModel: NewsFragmentViewModel by viewModel()
    private val newsAdapter by lazy { NewsAdapter(onNewsClickListener) }
    private val binding by viewBinding(FragmentNewsBinding::bind)

    private val onNewsClickListener: OnNewsClickListener = object : OnNewsClickListener {

        override fun onIconDeleteClickListener(news: News) {
            newsViewModel.deleteNewsFromRoom(news)
        }

        override fun onIconSaveClickListener(news: News) {
            newsViewModel.saveNewsToRoom(news)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }


    private fun initRecycler() {
        binding.recyclerView.adapter = newsAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        newsViewModel.newsForRecyclerView.observe(viewLifecycleOwner, { news ->
            newsAdapter.addNews(news)
        })
    }

}