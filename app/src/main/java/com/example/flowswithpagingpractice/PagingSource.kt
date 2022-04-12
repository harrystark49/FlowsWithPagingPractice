package com.example.flowswithpagingpractice

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class Pagingsrc(var retroService: Retro_Service):PagingSource<Int,Results>() {
    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        var currentPos= params.key?:1
        var data=retroService.getdata(currentPos)

        Log.d("data1","data ${data.results}")
        return LoadResult.Page(data = data.results,prevKey = if(currentPos==1)null else currentPos-1,nextKey = if(false)null else currentPos+1)
    }


}