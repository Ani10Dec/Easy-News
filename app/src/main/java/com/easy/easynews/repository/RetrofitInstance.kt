package com.easy.easynews.repository

import com.easy.easynews.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitApi: NetworkCall = retrofit.create(NetworkCall::class.java)
    }
}