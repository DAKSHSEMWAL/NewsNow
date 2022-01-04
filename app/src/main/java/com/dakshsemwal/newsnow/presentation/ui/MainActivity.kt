package com.dakshsemwal.newsnow.presentation.ui

import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.dakshsemwal.newsnow.R
import com.dakshsemwal.newsnow.common.EndlessRecyclerViewScrollListener
import com.dakshsemwal.newsnow.databinding.ActivityMainBinding
import com.dakshsemwal.newsnow.domain.model.Article
import com.dakshsemwal.newsnow.presentation.ui.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private var _binding: ActivityMainBinding? = null

    private lateinit var list: ArrayList<Article>
    private lateinit var temp: ArrayList<Article>

    private lateinit var adapter: NewsAdapter

    private var cardAdapter: PagerAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var totalInThisPage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObservers()
    }

    //Initialize ui Component
    private fun setupUI() {
        list = ArrayList()
        temp = ArrayList()
        _binding!!.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(list)
        adapter.SetOnItemClickListener(
            object : NewsAdapter.ListItemClickListener {
                override fun onItemClick(listItem: Article, position: Int) {

                }
            })


        binding.recyclerView.adapter = adapter
        val scrollListener: EndlessRecyclerViewScrollListener =
            object :
                EndlessRecyclerViewScrollListener(_binding!!.recyclerView.layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    Log.e("TotalPage,","$totalInThisPage")
                    homeViewModel.getNews()
                }
            }
        binding.recyclerView.addOnScrollListener(scrollListener)
    }

    private fun setupObservers() {
        homeViewModel.state.observe(this, {
            it.let { resource ->
                if (resource.isLoading) {

                } else {
                    if (resource.error.isNotBlank()) {
                    } else {
                        totalInThisPage =resource.newsListDTO?.totalResults ?: 0
                        resource.newsList?.let { movieList ->
                            list.addAll(movieList)
                            adapter.submitList(list)
                        }
                    }
                }
            }
        })
    }

}