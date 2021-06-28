package com.example.harajtask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val body: String,
    val city: String,
    val commentCount: Int,
    val date: Long,
    val thumbURL: String,
    val title: String,
    val username: String
):Parcelable