package com.test.mazetv.data.models

data class Episode(
    val id: Int,
    val url: String,
    val name: String,
    val season: Int,
    val number: Int,
    val airdate: String,
    val airtime: String,
    val runtime: Int?,
    val rating: Rating,
    val image: Image?,
    val summary: String?,
)
