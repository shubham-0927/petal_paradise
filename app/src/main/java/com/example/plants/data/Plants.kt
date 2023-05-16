package com.example.plants.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Plants(
    @PrimaryKey
    var _id: String = "",

    var category: String? = null,

    var commonname: String? = null,

    var details: String? = null,

    var name: String? = null,

    var size: String? = null,

    var space: String? = null,

    var sunlight: String? = null,

    var temperature: String? = null,

    var image: String? = null,

    var watering: String? = null
): RealmObject() {}

