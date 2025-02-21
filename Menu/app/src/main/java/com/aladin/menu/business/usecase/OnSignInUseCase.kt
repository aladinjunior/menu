package com.aladin.menu.business.usecase

import com.aladin.menu.business.model.UserAccount

interface OnSignInUseCase {
    operator fun invoke() : List<UserAccount>?
}