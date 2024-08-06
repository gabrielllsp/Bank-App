package com.gabriel.bankapp.domain.deposit

import com.gabriel.bankapp.data.model.Deposit
import com.gabriel.bankapp.data.repository.deposit.DepositDataSourceImpl
import javax.inject.Inject

class SaveDepositUseCase @Inject constructor(
    private val depositDataSourceImpl: DepositDataSourceImpl
) {
    suspend operator fun invoke(deposit: Deposit):String {
        return depositDataSourceImpl.saveDeposit(deposit)
    }
}