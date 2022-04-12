package com.example.flowswithpagingpractice

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repo(var retroService: Retro_Service) {

    fun getData():Flow<PagingData<Results>>{
        return Pager(config = PagingConfig(10,maxSize = 100),pagingSourceFactory = {
            Pagingsrc(retroService)
        }).flow
    }
}