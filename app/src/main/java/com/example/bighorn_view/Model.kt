package com.example.bighorn_view

data class Device(
    val title : String,
    val image : String
)

data class Data(
    val temperature_1 : String,
    val temperature_2 : String,
    val temperature_3 : String,
    val turbidity_1 : String,
    val turbidity_2 : String,
    val turbidity_3 : String,
    val water_level_1 : String,
    val water_level_2 : String,
    val water_level_3 : String
)