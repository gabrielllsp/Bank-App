package com.gabriel.bankapp.domain.profile

import com.gabriel.bankapp.data.model.User
import com.gabriel.bankapp.data.repository.profile.ProfileRepositoryImpl
import javax.inject.Inject

class SaveProfileUseCase @Inject constructor(
    private val profileRepositoryImpl: ProfileRepositoryImpl
) {
    suspend operator fun invoke(user: User) {
        profileRepositoryImpl.saveProfile(user)
    }

}