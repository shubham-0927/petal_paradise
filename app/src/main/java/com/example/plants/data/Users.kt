package com.example.plants.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Users(
    @PrimaryKey
    var _id: String = "",

    var address: String = "",

    var dob: String = "",

    var email: String = "",

    var image: String = "",

    var mobilenumber: String = "",

    var username: String = ""
): RealmObject() {}
