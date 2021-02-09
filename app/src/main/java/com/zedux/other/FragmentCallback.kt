package com.zedux.other

interface FragmentCallback {

    fun navigateToHome()
    fun navigateLoginToSignUp()
    fun navigateSignUpToLogin()
    fun navigateOnboardToContinue()
    fun navigateContinueToSignUp()
    fun navigateSignUpToSelectCourses()
    fun navigateSelectCoursesToCoursesSelected()
    fun navigateSelectCoursesToHome()
    fun navigateCoursesSelectedToHome()
}