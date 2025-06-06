package com.example.mobile.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel




class StudentViewModel : ViewModel() {
    private val _userId = mutableStateOf<Int?>(null)
    val userId: State<Int?> = _userId

    private val _groupId = mutableStateOf<Int?>(null)
    val groupId: State<Int?> = _groupId

    private val _type = mutableStateOf<String?>(null)
    val type: State<String?> = _type

    fun saveUserInfo(id: Int, groupId: Int, type: String) {
        _userId.value = id
        _groupId.value = groupId
        _type.value = type
    }
}
