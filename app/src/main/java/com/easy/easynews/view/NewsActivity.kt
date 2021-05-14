package com.easy.easynews.view

//import com.easy.easynews.repository.NewsServices
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.easy.easynews.R
import com.easy.easynews.modal.NewsPojo
import com.easy.easynews.utils.AppHelper
import com.easy.easynews.view_model.NewsAdapter
import com.easy.easynews.view_model.NewsViewModel

class NewsActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView
    lateinit var newsAdapter: NewsAdapter
    lateinit var progressLayout: RelativeLayout
    var newArrayList = arrayListOf<NewsPojo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        // HOOKS
        swipeRefreshLayout = findViewById(R.id.refresh_news)
        recyclerView = findViewById(R.id.recyclerView)
        progressLayout = findViewById(R.id.progress_layout)
        recyclerView.layoutManager = LinearLayoutManager(this)
        progressLayout.visibility = View.VISIBLE

        if (AppHelper.isOnline(applicationContext)) {
            fetchData()
        } else {
            progressLayout.visibility = View.GONE
            Toast.makeText(this, "Please check you internet connection", Toast.LENGTH_LONG).show()
        }

        swipeRefreshLayout.setOnRefreshListener {
            if (AppHelper.isOnline(applicationContext)) {
                progressLayout.visibility = View.VISIBLE
                swipeRefreshLayout.isRefreshing = true
                newArrayList.clear()
                fetchData()
            } else {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(this, "Please check you internet connection", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun fetchData() {
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        }
        val viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.getDataObserver().observe(this, {
            if (it != null) {
                newArrayList.clear()
                progressLayout.visibility = View.GONE
                newArrayList.addAll(it.articles)
                newsAdapter = NewsAdapter(applicationContext, newArrayList)
                newsAdapter.notifyDataSetChanged()
                recyclerView.adapter = newsAdapter
            } else
                Toast.makeText(this, "failed", Toast.LENGTH_LONG).show()
        })
        viewModel.callApi()
    }

    private var doubleBackBtn = false

    override fun onBackPressed() {
        if (doubleBackBtn) {
            super.onBackPressed()
            return
        }
        doubleBackBtn = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        val handler = Handler()
        handler.postDelayed({ doubleBackBtn = false }, 2000)
    }
}
