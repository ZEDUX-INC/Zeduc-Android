package com.zedux.data.repositories

import com.zedux.data.firebase.FirebaseSource

class AuthRepository (private val firebaseSource: FirebaseSource) {

     fun register(email: String, password: String) = firebaseSource.register(email, password)

     fun login(email: String, password: String) = firebaseSource.login(email, password)

     fun currentUser() = firebaseSource.currentUser()

     fun logOut() = firebaseSource.logOUt()


}