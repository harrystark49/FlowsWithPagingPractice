package com.example.flowswithpagingpractice

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class Viewmodel(var retroService: Retro_Service):ViewModel(){

    var repo=Repo(retroService)
    var _result=MutableStateFlow<PagingData<Results>>(PagingData.empty())
    var result=_result

    fun getdata(){
        viewModelScope.launch {
            repo.getData().collect {
                Log.d("datais","$it")
                _result.value=it
            }
        }

    }

}