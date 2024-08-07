package com.gabriel.bankapp.presenter.features.deposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gabriel.bankapp.R
import com.gabriel.bankapp.databinding.FragmentDepositReceiptBinding
import com.gabriel.bankapp.util.GetMask


class DepositReceiptFragment : Fragment() {
    private var _binding: FragmentDepositReceiptBinding? = null
    private val binding get() = _binding!!

    private val args: DepositReceiptFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepositReceiptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configData()
        initListener()
    }

    private fun initListener() {
        binding.btnNext.setOnClickListener { findNavController().popBackStack() }
    }

    private fun configData() {
        binding.codTransaction.text = args.deposit.id
        binding.textDateTransaction.text = GetMask.getFormatedDate(args.deposit.date, GetMask.DAY_MONTH_YEAR_HOUR_MINUTE)
        binding.textAmountTransaction.text =
            getString(R.string.text_formated_value, GetMask.getFormatedValue(args.deposit.amount))
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
