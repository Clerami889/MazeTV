package com.test.mazetv.data.models

data class ShowsDetails(
    val shows: Shows,
    val seasons: List<Season>,
    val episodes: List<Episode>,
    val cast: List<Cast>,
)
