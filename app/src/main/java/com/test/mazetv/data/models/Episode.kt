package com.test.mazetv.data.models

data class Episode(
    val id: Int,
    val name: String,
    val season: Int,
    val number: Int,
    val image: Image?,
)
