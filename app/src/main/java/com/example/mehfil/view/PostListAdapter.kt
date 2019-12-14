package com.example.mehfil.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mehfil.R
import com.example.mehfil.model.Post
import com.example.mehfil.util.getProgressDrawable
import com.example.mehfil.util.loadImage
import kotlinx.android.synthetic.main.item_post.view.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PostListAdapter(var posts: ArrayList<Post>) :
    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
    )

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val BASE_URL = "http://nr-mehfil.herokuapp.com/"
        private val imageView = view.post_image
        private val postBody = view.post_body
        private val postUsername = view.post_username
        private val postDate = view.post_date
        private val progressDrawable = getProgressDrawable(view.context)
        private val simpleDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)

        fun bind(post: Post) {
            val ts = Timestamp(post.createdAt!!.toLong())
            val date = Date(ts.time)
            val username =
                post.createdBy.name.firstName + " " + post.createdBy.name.lastName
            Log.d("Image URL", BASE_URL + post.media)
            postBody.text = post.body
            postUsername.text = username
            postDate.text = simpleDateFormat.format(date)
            imageView.loadImage(BASE_URL + post.media, progressDrawable)
        }
    }

    fun updatePosts(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }
}