package com.easy.easynews.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.easy.easynews.modal.NewsPojo
import com.easy.easynews.modal.ResultPOJO
import com.easy.easynews.repository.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsViewModel : ViewModel() {

    var listData: MutableLiveData<ResultPOJO> = MutableLiveData()

    fun getDataObserver(): MutableLiveData<ResultPOJO>{
        return listData
    }

    fun callApi(){
        RetrofitInstance.retrofitApi.getNewsData()
            .enqueue(object : Callback<ResultPOJO>{
                override fun onResponse(call: Call<ResultPOJO>, response: Response<ResultPOJO>) {

                    if (response.isSuccessful){
                        val news = response.body()
                        listData.postValue(news)
                    }
                }

                override fun onFailure(call: Call<ResultPOJO>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}