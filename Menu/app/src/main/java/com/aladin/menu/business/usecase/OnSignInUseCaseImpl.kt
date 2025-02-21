package com.aladin.menu.business.usecase

import android.util.Log
import com.aladin.menu.business.data.UserRepository
import com.aladin.menu.business.model.UserAccount

private val TAG = "signInLogCatError"

class OnSignInUseCaseImpl(
    private val userRepository: UserRepository
) : OnSignInUseCase {
    override fun invoke() : List<UserAccount>? {
        return try {
            userRepository.getAllUserAccounts()
        } catch (e: Exception) {
            Log.e(TAG, "error on signin usecase: ${e.message}", )
            null
        }
    }
}