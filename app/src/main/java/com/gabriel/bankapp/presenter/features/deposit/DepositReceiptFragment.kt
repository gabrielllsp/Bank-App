package com.gabriel.bankapp.presenter.features.deposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gabriel.bankapp.R
import com.gabriel.bankapp.data.model.Deposit
import com.gabriel.bankapp.databinding.FragmentDepositReceiptBinding
import com.gabriel.bankapp.util.GetMask
import com.gabriel.bankapp.util.StateView
import com.gabriel.bankapp.util.initToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DepositReceiptFragment : Fragment() {
    private var _binding: FragmentDepositReceiptBinding? = null
    private val binding get() = _binding!!

    private val args: DepositReceiptFragmentArgs by navArgs()
    private val depositReceiptViewModel: DepositReceiptViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepositReceiptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar, args.homeAsUpEnabled)
        getDeposit()
        initListener()
    }

    private fun getDeposit() {
        depositReceiptViewModel.getDeposit(args.idDeposit)
            .observe(viewLifecycleOwner) { stateView ->
                when (stateView) {

                    is StateView.Loading -> {

                    }

                    is StateView.Sucess -> {
                        stateView.data?.let { configData(it) }
                    }

                    is StateView.Error -> {
                        Toast.makeText(requireContext(), "Ocorreu um erro", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().popBackStack()
                    }
                }
            }
    }

    private fun initListener() {
        binding.btnNext.setOnClickListener { findNavController().popBackStack() }
    }

    private fun configData(deposit: Deposit) {
        binding.codTransaction.text = deposit.id
        binding.textDateTransaction.text =
            GetMask.getFormatedDate(deposit.date, GetMask.DAY_MONTH_YEAR_HOUR_MINUTE)
        binding.textAmountTransaction.text =
            getString(R.string.text_formated_value, GetMask.getFormatedValue(deposit.amount))
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
