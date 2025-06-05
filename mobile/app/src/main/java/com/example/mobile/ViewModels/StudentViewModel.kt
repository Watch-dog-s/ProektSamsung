package com.example.mobile.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel




class StudentViewModel : ViewModel() {

    var marks by mutableStateOf<List<MarkResponse>>(emptyList())
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun setMarks(newMarks: List<MarkResponse>) {
        marks = newMarks
    }

    fun setError(message: String) {
        error = message
    }
}
