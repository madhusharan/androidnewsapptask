package com.newsfeed.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.newsfeed.app.adapter.NewsAdapter
import com.newsfeed.app.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsAdapter = NewsAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = newsAdapter

        val swipeRefreshLayout: SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            newsViewModel.fetchNews("business") // Fetch news based on selected category
            swipeRefreshLayout.isRefreshing = false
        }

        // Observe LiveData
        newsViewModel.newsArticles.observe(this, Observer {
            newsAdapter.submitList(it)
        })

        // Initial data fetch
        newsViewModel.fetchNews("business")
    }
}


