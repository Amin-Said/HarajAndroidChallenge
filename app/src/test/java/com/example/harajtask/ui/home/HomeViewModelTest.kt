package com.example.harajtask.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.harajtask.getOrAwaitValueTest
import com.example.harajtask.repository.FakeRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest{

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = HomeViewModel(FakeRepository())
    }

    @Test
    fun fetchAllDataTest(){
        viewModel.fetchAllData()
        val result = viewModel.result.getOrAwaitValueTest()
        Assert.assertNotNull(result)
        Assert.assertFalse(result.isEmpty())
        Assert.assertEquals(result[0].username,"me")

    }

}