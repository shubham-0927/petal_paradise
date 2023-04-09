package com.example.plants.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plants.data.Users
import org.tensorflow.lite.support.image.TensorImage
import java.net.Inet4Address

class UserDetailsViewModel :ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email
    private val _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username
    private val _mobilenumber = MutableLiveData<String>()
    val mobilenumber: LiveData<String>
        get() = _mobilenumber
    private val _dob = MutableLiveData<String>()
    val dob: LiveData<String>
        get() = _dob
    private val _address = MutableLiveData<String>()
    val address: LiveData<String>
        get() = _address
    private val _image = MutableLiveData<String>()
    val image: LiveData<String>
        get() = _image
    fun setValues(email: String, username: String, mobilenumber: String,dob: String,address: String,image: String){
        _email.value = email
        _username.value = username
        _mobilenumber.value = mobilenumber
        _dob.value = dob
        _address.value = address
        _image.value = image
    }
}