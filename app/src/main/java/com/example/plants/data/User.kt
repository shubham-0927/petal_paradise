package com.example.plants.data

data class User(val username: String,val email:String, val password: String){
    constructor(): this("","","")
}