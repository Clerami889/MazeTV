package com.test.mazetv.data.models

data class Season(
    val id: Int,
    val number: Int,
    val name: String?,
    val episodeOrder: Int?,
    val premiereDate: String?,
    val endDate: String?,
    val image: Image?,
    val summary: String?,
)
