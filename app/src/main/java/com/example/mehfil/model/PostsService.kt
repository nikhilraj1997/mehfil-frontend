package com.example.mehfil.model

import com.example.mehfil.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class PostsService {
    @Inject
    lateinit var postsApi: PostsApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getPosts(): Single<List<Post>> {
        return postsApi.getPosts()
    }
}