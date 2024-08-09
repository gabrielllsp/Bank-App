package com.gabriel.bankapp.presenter.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gabriel.bankapp.R
import com.gabriel.bankapp.data.model.Transaction
import com.gabriel.bankapp.data.transaction_enum.TransactionOperation
import com.gabriel.bankapp.data.transaction_enum.TransactionType
import com.gabriel.bankapp.databinding.FragmentHomeBinding
import com.gabriel.bankapp.util.FirebaseHelper
import com.gabriel.bankapp.util.GetMask
import com.gabriel.bankapp.util.StateView
import com.gabriel.bankapp.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapterTransaction: TransactionsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
        getTransaction()
        initListeners()
    }

    private fun configRecyclerView() {
        adapterTransaction = TransactionsAdapter(requireContext()) { transaction ->
            when(transaction.operation){
                TransactionOperation.DEPOSIT -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToDepositReceiptFragment(transaction.id, true)
                    findNavController().navigate(action)
                }else -> {}
            }

        }
        with(binding.rvTransactions){
            setHasFixedSize(true)
            adapter = adapterTransaction
        }
    }

    private fun initListeners(){

        binding.imageLogout.setOnClickListener {
           FirebaseHelper.getAuth().signOut()
            findNavController().navigate(R.id.action_homeFragment_to_authentication)

        }
        binding.btnShowAll.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_extractFragment)
        }

        binding.cardExtract.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_extractFragment)
        }

        binding.cardDeposit.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_depositFormFragment)
        }

    }

    private fun getTransaction() {
        homeViewModel.getTransaction().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {

                is StateView.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is StateView.Sucess -> {
                    binding.progressBar.isVisible = false

                    adapterTransaction.submitList(stateView.data?.reversed()?.take(6))

                    showBalance(stateView.data ?: emptyList())
                }

                is StateView.Error -> {
                    binding.progressBar.isVisible = false
                    showBottomSheet(message = stateView.message)
                }
            }
        }
    }

    private fun showBalance(transactions: List<Transaction>) {
        var cashIn = 0f
        var cashOut = 0f

        transactions.forEach{ transactions ->
            if(transactions.type == TransactionType.CASH_IN){
                cashIn += transactions.amount
            }else{
                cashOut += transactions.amount
            }
        }
        binding.valueBalance.text =
            getString(R.string.text_formated_value, GetMask.getFormatedValue(cashIn - cashOut))

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}