package com.example.myapplication.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class LoginViewModel: ViewModel(){

    private val _success= MutableStateFlow(false)
    val success: StateFlow<Boolean> = _success.asStateFlow()

    fun changeTrue() {
        viewModelScope.launch {
            _success.value = true
        }


    }

    fun changeFalse() {
        viewModelScope.launch {
            _success.value = false
        }
    }


}