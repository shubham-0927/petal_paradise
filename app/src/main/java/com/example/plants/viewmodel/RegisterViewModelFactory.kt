package com.example.plants.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.realm.mongodb.App

class RegisterViewModelFactory(private val app: App) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}