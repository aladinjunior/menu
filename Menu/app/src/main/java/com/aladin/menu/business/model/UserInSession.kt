package com.aladin.menu.business.model

data class UserInSession(
    val id: Int,
    val username: String,
    val timeInSessionTimeStamp: Long
)
