package com.gabriel.bankapp.presenter.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gabriel.bankapp.domain.auth.RegisterUseCase
import com.gabriel.bankapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    fun register(name: String, email: String, phone: String, password: String) =
        liveData(Dispatchers.IO) {
            try {
                emit(StateView.Loading())
                val user = registerUseCase.invoke(name, email,phone ,password)
                emit(StateView.Sucess(user))
            } catch (ex: Exception) {
                emit(StateView.Error(ex.message))
            }
        }
}