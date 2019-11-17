package com.example.demonav.host

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HostViewModel : ViewModel() {

    companion object {

        private const val VALID_USERNAME = "zavanton"
        private const val VALID_PASSWORD = "1234"
    }

    sealed class Status {

        object Unauthenticated : Status()

        object InvalidAuthentication : Status()

        data class Authenticated(val username: String) : Status()
    }

    var status: MutableLiveData<Status> = MutableLiveData()

    init {
        status.value = Status.Unauthenticated
    }

    fun loginUser(username: String, password: String) {
        if (username == VALID_USERNAME && password == VALID_PASSWORD) {
            status.value = Status.Authenticated(VALID_USERNAME)
        } else {
            status.value = Status.InvalidAuthentication
        }
    }
}