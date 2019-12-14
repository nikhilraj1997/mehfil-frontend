package com.example.mehfil.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface PostsApi {
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImFiY0B4eXouY29tIiwidXNlcklkIjoiNWRlYjIzMDk4NzA5MjEzZDI4NjcyNWU5IiwibmFtZSI6eyJmaXJzdE5hbWUiOiJUZXN0IiwibGFzdE5hbWUiOiJVc2VyIn0sImlhdCI6MTU3NTY5MTExMSwiZXhwIjoxNTc4MjgzMTExfQ.Z--eYAN1W_LXfsZ5VwRaBljUQmAcME6x_mEI2Z_moxw")
    @GET("post")
    fun getPosts(): Single<List<Post>>
}