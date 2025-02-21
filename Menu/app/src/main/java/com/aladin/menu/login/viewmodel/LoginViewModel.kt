package com.aladin.menu.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aladin.menu.business.usecase.OnSignInUseCase


class LoginViewModel(
    private val onSignInUseCase: OnSignInUseCase
) : ViewModel() {

    private val isValidForm = MutableLiveData<Boolean>()
    val isValidFormLiveData: LiveData<Boolean> = isValidForm
    private val errorMessage = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String> = errorMessage

    fun validateAndGoToMainScreen(name: String, phone: String) {
        when {
            name.isEmpty() -> {
                errorMessage.postValue("name can't be empty.")
                isValidForm.postValue(false)
            }
            phone.isEmpty() -> {
                errorMessage.postValue("phone can't be empty.")
                isValidForm.postValue(false)
            }
            else -> isValidForm.postValue(true)

        }
    }


}