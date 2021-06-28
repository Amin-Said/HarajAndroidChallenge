package com.example.harajtask.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RepositoryTest{
    lateinit var context:Context
    lateinit var repository: Repository

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        repository = Repository(context)
    }

   @Test
   fun getDataFromJsonTest(){
       val result = repository.getDataFromJson()
       assertNotNull(result)
       assertFalse(result?.isEmpty()!!)
       assertEquals(result?.get(0)?.username,"ffaris6")
   }
}