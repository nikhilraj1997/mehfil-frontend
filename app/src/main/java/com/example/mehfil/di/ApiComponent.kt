package com.example.mehfil.di

import com.example.mehfil.model.PostsService
import com.example.mehfil.viewmodel.PostViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: PostsService)
    fun inject(postViewModel: PostViewModel)
}