package com.zedux.data.interfaces

interface AuthListener {

    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}