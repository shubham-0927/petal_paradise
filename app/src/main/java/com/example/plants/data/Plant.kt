package com.example.plants.data

data class Plant(
    val id: Int,
    val name: String,
    val commonname: String,
    val imageUrl: String ="",
    val desc: String,
    val size: String ="",
    val space: String ="",
    val sunlight: String ="",
    val temperature: String ="",
    val watering: String = ""
)

