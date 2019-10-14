package com.momo.zoogov.data

import java.io.Serializable

data class PlantsResults(
    val F_Name_Ch: String? = "",
    val F_Name_En: String? = "",
    val F_Pic01_URL: String? = "",
    val F_AlsoKnown: String? = "",  // 別名
    val F_Brief: String? = "",      // 簡介
    val F_Feature: String? = ""     // 辨認方式
) : Serializable