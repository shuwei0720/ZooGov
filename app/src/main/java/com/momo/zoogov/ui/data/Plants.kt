package com.momo.zoogov.data

data class Plants(
    val count: Int? = 0,
    val limit: Int? = 0,
    val offset: Int? = 0,
    val results: List<PlantsResults> = listOf()
)