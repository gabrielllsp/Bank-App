package com.gabriel.bankapp.presenter.features.deposit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gabriel.bankapp.data.model.Deposit
import com.gabriel.bankapp.domain.deposit.SaveDepositUseCase
import com.gabriel.bankapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DepositViewModel @Inject constructor(
    private val saveDepositUseCase: SaveDepositUseCase
) : ViewModel() {

    fun saveDeposit(deposit: Deposit) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val result = saveDepositUseCase.invoke(deposit)

            emit(StateView.Sucess(result))
        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }
}