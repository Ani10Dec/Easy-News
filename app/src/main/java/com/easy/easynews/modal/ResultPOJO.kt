package com.easy.easynews.modal

data class ResultPOJO(
    val status: String,
    val articles: List<NewsPojo>,
    val totalResults: Int
)