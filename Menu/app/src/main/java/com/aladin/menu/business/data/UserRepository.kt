package com.aladin.menu.business.data

import com.aladin.menu.business.model.UserAccount

interface UserRepository {
    fun getAllUserAccounts() : List<UserAccount>
}