package com.example.plants.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plants.data.UserData
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.runBlocking


class RegisterViewModel(private val app: io.realm.mongodb.App) : ViewModel() {
    private val _registrationResult = MutableLiveData<Result<User>>()
    val registrationResult: LiveData<Result<User>> = _registrationResult

     fun registerUsers(userData: UserData){
        val credentials = Credentials.emailPassword(userData.email,userData.password)
        runBlocking {
            app.emailPassword.registerUserAsync(
                userData.email,
                userData.password
            ){ result ->
                if (result.isSuccess) {

                } else {

                }
            }

            }
        }
}