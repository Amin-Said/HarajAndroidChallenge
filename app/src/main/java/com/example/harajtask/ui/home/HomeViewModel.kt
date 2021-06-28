package com.example.harajtask.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harajtask.model.Post
import com.example.harajtask.repository.IRepository

class HomeViewModel @ViewModelInject constructor(
    private val repository: IRepository
): ViewModel(){
    var result: MutableLiveData<List<Post>> = MutableLiveData()

    fun fetchAllData(){
        repository.getDataFromJson().also { result.value = it }
    }


}