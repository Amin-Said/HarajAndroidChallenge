package com.example.harajtask.repository

import com.example.harajtask.model.Post

class FakeRepository : IRepository {
    private val posts = mutableListOf<Post>()

    override fun getDataFromJson(): List<Post>? {
        posts.add(Post("body mock", "CAIRO", 20, 1620743395
            , "url", "mock title", "me"))
        return posts
    }
}