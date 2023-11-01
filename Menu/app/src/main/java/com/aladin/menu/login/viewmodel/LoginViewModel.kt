package com.aladin.menu.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LoginViewModel : ViewModel() {

    private val isValidForm = MutableLiveData<Boolean>()
    val isValidFormLiveData: LiveData<Boolean> = isValidForm
    private val errorMessage = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String> = errorMessage

    fun validateAndGoToMainScreen(name: String, phone: String) {
        if (name.isNotEmpty() && phone.isNotEmpty() && phone.length == 11) {
            isValidForm.postValue(true)
        } else {
            isValidForm.postValue(false)
            errorMessage.postValue("Nenhum campo pode ser vazio!")
        }
    }


}