package com.test.mazetv.data.models

data class Shows(
    val id: Int,
    val url: String,
    val rating: Rating,
    val image: Image,
    val name: String,
    val summary: String,
    val premiered: String,
)
