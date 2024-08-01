package com.gabriel.bankapp.presenter.auth.register

import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel
import com.gabriel.bankapp.data.model.User
import com.gabriel.bankapp.domain.auth.RegisterUseCase
import com.gabriel.bankapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel()  {
    fun register(user: User) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            registerUseCase.invoke(user)
            emit(StateView.Sucess(user))
        }catch (ex: Exception){
            emit(StateView.Error(ex.message))
        }
    }
}