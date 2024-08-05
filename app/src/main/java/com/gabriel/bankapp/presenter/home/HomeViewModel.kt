package com.gabriel.bankapp.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gabriel.bankapp.domain.wallet.GetWalletUseCase
import com.gabriel.bankapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWalletUseCase: GetWalletUseCase
) : ViewModel() {
    fun getWallet() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            getWalletUseCase.invoke()

            emit(StateView.Sucess(null))
        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }
}