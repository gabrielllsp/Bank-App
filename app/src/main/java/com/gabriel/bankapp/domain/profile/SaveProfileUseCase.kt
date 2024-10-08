package com.gabriel.bankapp.domain.profile

import com.gabriel.bankapp.data.model.User
import com.gabriel.bankapp.data.repository.profile.ProfileDataSourceImpl
import javax.inject.Inject

class SaveProfileUseCase @Inject constructor(
    private val profileDataSourceImpl: ProfileDataSourceImpl
) {
    suspend operator fun invoke(user: User) {
        return profileDataSourceImpl.saveProfile(user)
    }

}