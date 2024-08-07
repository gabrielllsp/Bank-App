package com.gabriel.bankapp.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gabriel.bankapp.domain.transaction.GetTransactionUseCase
import com.gabriel.bankapp.domain.wallet.GetWalletUseCase
import com.gabriel.bankapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWalletUseCase: GetWalletUseCase,
    private val getTransactionUseCase: GetTransactionUseCase
) : ViewModel() {
    fun getWallet() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val wallet = getWalletUseCase.invoke()

            emit(StateView.Sucess(wallet))
        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }

    fun getTransaction() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val transactions = getTransactionUseCase.invoke()

            emit(StateView.Sucess(transactions))
        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }
}