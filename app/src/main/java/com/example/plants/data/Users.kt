package com.example.plants.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId
open class Users: RealmObject(){
    @PrimaryKey
    var _id: String =""
    @Required
    var email:String=""
    var username: String =""
    var mobilenumber:String=""
    var dob: String=""

    var address: String =""

    var image:String=""
}