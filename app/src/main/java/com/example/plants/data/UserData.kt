package com.example.plants.data

data class UserData( val email:String, val password: String, val username: String ="", val address: String ="", val image:String=""){
    constructor(): this("","","","","")
}