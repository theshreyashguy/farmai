package com.example.farmai

data class Data(
    val Humidity: String,
    val Raindrops: String,
    val Soilmoisture: String,
    val Temperature: List<String>,
    val Temperatureyesterday: List<String>,
    val Waterpump: String,
    val __v: Int,
    val _id: String
)