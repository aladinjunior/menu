package com.aladin.menu.business.signinscope

import com.aladin.menu.business.model.UserAccount

fun loadUserAccountsList(): List<UserAccount> {
    return listOf(
        UserAccount(1, "user1", "password1"),
        UserAccount(2, "user2", "password2"),
        UserAccount(3, "user3", "password3"),
        UserAccount(4, "user4", "password4"),
        UserAccount(5, "user5", "password5"),
        UserAccount(6, "user6", "password6"),
        UserAccount(7, "user7", "password7"),
        UserAccount(8, "user8", "password8"),
        UserAccount(9, "user9", "password9"),
        UserAccount(10, "user10", "password10")
    )
}
