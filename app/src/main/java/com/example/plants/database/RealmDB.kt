package com.example.plants.database

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration


class RealmDB : Application() {
    override fun onCreate() {
        super.onCreate()

        // on below line we are
        // initializing our realm database.
        Realm.init(this)

        // on below line we are setting realm configuration
        val config = RealmConfiguration.Builder() // below line is to allow write
            // data to database on ui thread.
            .allowWritesOnUiThread(true) // below line is to delete realm
            // if migration is needed.
            .deleteRealmIfMigrationNeeded() // at last we are calling a method to build.
            .build()
        // on below line we are setting
        // configuration to our realm database.
        Realm.setDefaultConfiguration(config)
        Realm.getInstanceAsync(config, object : Realm.Callback() {
            override fun onSuccess(realm: Realm) {
                Log.v(
                    "EXAMPLE",
                    "Successfully opened a realm with reads and writes allowed on the UI thread."
                )
            }
        })
    }

}
