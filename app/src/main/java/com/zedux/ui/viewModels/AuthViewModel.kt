package com.zedux.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.zedux.data.firebase.FirebaseSource
import com.zedux.data.interfaces.AuthListener
import com.zedux.data.repositories.AuthRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel : ViewModel() {

    private val authRepository by lazy {
        AuthRepository(FirebaseSource())
    }

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    val user by lazy {
        authRepository.currentUser()
    }

    private val disposables = CompositeDisposable()

     fun signUp() {

         Log.d("SignUp", "Signing...")
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Email or password is missing")
            return
        }

         Log.d("SignUp", "not empty...")
        authListener?.onStarted()
        val disposable = authRepository.register(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { authListener?.onSuccess() },
                { authListener?.onFailure(it.message!!) }
            )
        disposables.add(disposable)
    }

     fun login() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Email or password is missing")
            return
        }
        val disposable = authRepository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { authListener?.onSuccess() },
                { authListener?.onFailure(it.message!!) }
            )
        disposables.add(disposable)
    }

    fun logOut() = authRepository.logOut()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }


}