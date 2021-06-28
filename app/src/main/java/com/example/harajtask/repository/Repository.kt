package com.example.harajtask.repository

import android.content.Context
import com.example.harajtask.model.Post
import com.example.harajtask.utils.readFile
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import javax.inject.Inject

interface IRepository {
    fun getDataFromJson(): List<Post>?
}

class Repository @Inject constructor(private val context: Context) : IRepository {
    override fun getDataFromJson(): List<Post>? {
        val dataResponse = context.assets.readFile("data.json")

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        var listMyData = Types.newParameterizedType(MutableList::class.java, Post::class.java)
        val jsonAdapter: JsonAdapter<List<Post>> =
            moshi.adapter(listMyData)

        return jsonAdapter.fromJson(dataResponse)

    }
}