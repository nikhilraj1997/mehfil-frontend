package com.example.mehfil.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mehfil.di.DaggerApiComponent
import com.example.mehfil.model.Post
import com.example.mehfil.model.PostsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel : ViewModel() {
    @Inject
    lateinit var  postsService: PostsService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val posts = MutableLiveData<List<Post>>()
    val postsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchPosts()
    }


    private fun fetchPosts() {
        loading.value = true
        postsLoadError.value = false
        disposable.add(
            postsService.getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Post>>() {
                    override fun onSuccess(value: List<Post>?) {
                        posts.value = value
                        postsLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("Error", e.toString())
                        postsLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}