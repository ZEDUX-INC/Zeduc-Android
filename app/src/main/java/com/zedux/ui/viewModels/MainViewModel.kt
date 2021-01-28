package com.zedux.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.zedux.data.firebase.FirebaseSource
import com.zedux.data.interfaces.AuthListener
import com.zedux.data.repositories.AuthRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val authRepository by lazy {
        AuthRepository(FirebaseSource())
    }


    val user by lazy {
        authRepository.currentUser()
    }

    fun logOut() = authRepository.logOut()


}