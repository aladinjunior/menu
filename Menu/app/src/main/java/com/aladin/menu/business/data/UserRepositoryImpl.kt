package com.aladin.menu.business.data

import com.aladin.menu.business.model.UserAccount
import com.aladin.menu.business.signinscope.LocalUserDataSource

class UserRepositoryImpl(
    private val localDataSource: LocalUserDataSource
) : UserRepository {
    override fun getAllUserAccounts(): List<UserAccount> {
        return localDataSource.requireUserAccounts()
    }

}