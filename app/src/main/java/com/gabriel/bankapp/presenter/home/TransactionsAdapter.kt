package com.gabriel.bankapp.presenter.home

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.bankapp.R
import com.gabriel.bankapp.data.model.Transaction
import com.gabriel.bankapp.data.transaction_enum.TransactionOperation
import com.gabriel.bankapp.data.transaction_enum.TransactionType
import com.gabriel.bankapp.databinding.TransactionItemBinding
import com.gabriel.bankapp.util.GetMask


class TransactionsAdapter(
    private val context: Context,
    private val transactionSelected: (Transaction) -> Unit
) : ListAdapter<Transaction, TransactionsAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Transaction>() {
            override fun areItemsTheSame(
                oldItem: Transaction,
                newItem: Transaction
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Transaction,
                newItem: Transaction
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TransactionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = getItem(position)

        transaction.operation?.let {
            holder.binding.textTransactionDescription.text = TransactionOperation.getOperation(it)

            holder.binding.textTransactionType.text = TransactionType.getType(it).toString()
            holder.binding.textTransactionType.backgroundTintList =
                if(transaction.type == TransactionType.CASH_IN){
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_cash_in))
            }else{
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_cash_out))
            }
        }

        holder.binding.textTransactionValue.text = GetMask.getFormatedValue(transaction.amount)
        holder.binding.textTransactionDate.text =
            GetMask.getFormatedDate(transaction.date, GetMask.DAY_MONTH_YEAR_HOUR_MINUTE)

        holder.itemView.setOnClickListener {
            transactionSelected(transaction)
        }

    }

    inner class ViewHolder(val binding: TransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}