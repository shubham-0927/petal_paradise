package com.example.plants.data

import io.realm.RealmObject

data class UserData(
    val email:String,
    val password: String,
) {
    constructor(): this("","")
}