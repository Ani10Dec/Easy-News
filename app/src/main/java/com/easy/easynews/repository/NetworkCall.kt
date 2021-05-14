package com.easy.easynews.repository

import com.easy.easynews.modal.ResultPOJO
import com.easy.easynews.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkCall {
    @GET("v2/everything")
    fun getNewsData(
        @Query("q") title: String = Constants.QUERY,
        @Query("page") pageNumber: Int = 1,
        @Query("sortBy") orderBy: String = Constants.ORDER_BY,
        @Query("language") lan: String = "en",
        @Query("apiKey") key: String = Constants.API_KEY
    ): Call<ResultPOJO>
}
