package com.gabriel.bankapp.presenter.features.extract

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gabriel.bankapp.data.transaction_enum.TransactionOperation
import com.gabriel.bankapp.databinding.FragmentExtractBinding
import com.gabriel.bankapp.presenter.home.TransactionsAdapter
import com.gabriel.bankapp.util.StateView
import com.gabriel.bankapp.util.initToolbar
import com.gabriel.bankapp.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExtractFragment : Fragment() {

    private var _binding: FragmentExtractBinding? = null
    private val binding get() = _binding!!

    private val extractViewModel: ExtractViewModel by viewModels()
    private lateinit var adapterTransaction: TransactionsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExtractBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar = binding.toolbar, light = true)
        configRecyclerView()
        getTransaction()
    }

    private fun configRecyclerView() {
        adapterTransaction = TransactionsAdapter(requireContext()) { transaction ->
            when (transaction.operation) {
                TransactionOperation.DEPOSIT -> {
                    val action =
                        ExtractFragmentDirections.actionExtractFragmentToDepositReceiptFragment(
                            transaction.id,
                            true
                        )

                    findNavController().navigate(action)
                }

                else -> {}
            }

        }
        with(binding.rvTransactions) {
            setHasFixedSize(true)
            adapter = adapterTransaction
        }
    }

    private fun getTransaction() {
        extractViewModel.getTransaction().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {

                is StateView.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is StateView.Sucess -> {
                    binding.progressBar.isVisible = false

                    adapterTransaction.submitList(stateView.data?.reversed())
                }

                is StateView.Error -> {
                    binding.progressBar.isVisible = false
                    showBottomSheet(message = stateView.message)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}