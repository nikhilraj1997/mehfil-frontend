package com.example.mehfil.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mehfil.R
import com.example.mehfil.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_mehfil.*

class MehfilFragment : Fragment() {
    private lateinit var postViewModel: PostViewModel
    private val postListAdapter = PostListAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mehfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        postViewModel.refresh()

        postList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postListAdapter
        }

        postSwipeRefreshLayout.setOnRefreshListener {
            postSwipeRefreshLayout.isRefreshing = false
            postViewModel.refresh()
        }

        observePostViewModel()
    }

    fun observePostViewModel() {
        postViewModel.posts.observe(this, Observer { posts ->
            posts?.let {
                postList.visibility = View.VISIBLE
                postListAdapter.updatePosts(it)
            }
        })

        postViewModel.postsLoadError.observe(this, Observer { isError ->
            isError?.let {
                post_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        postViewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_posts.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    post_error.visibility = View.GONE
                    postList.visibility = View.GONE
                }
            }
        })
    }
}