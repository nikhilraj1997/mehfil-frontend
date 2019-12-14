package com.example.mehfil.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("_id")
    val id : String?,
    @SerializedName("body")
    val body: String?,
    @SerializedName("media")
    val media: String?,
    @SerializedName("createdAt")
    val createdAt: Number?,
    @SerializedName("createdBy")
    val createdBy: PostCreatedBy
)

data class PostCreatedBy(
    @SerializedName("name")
    val name: UserName,
    @SerializedName("userId")
    val userId: String
)

data class UserName(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String
)