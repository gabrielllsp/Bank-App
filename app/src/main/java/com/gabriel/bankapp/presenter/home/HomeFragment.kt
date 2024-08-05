package com.gabriel.bankapp.presenter.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gabriel.bankapp.R
import com.gabriel.bankapp.data.model.Wallet
import com.gabriel.bankapp.databinding.FragmentHomeBinding
import com.gabriel.bankapp.util.GetMask
import com.gabriel.bankapp.util.StateView
import com.gabriel.bankapp.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getWallet()
    }

    private fun getWallet() {
        homeViewModel.getWallet().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {

                is StateView.Loading -> {

                }

                is StateView.Sucess -> {
                    stateView.data?.let { showBalance(it) }
                }

                is StateView.Error -> {
                    showBottomSheet(message = stateView.message)
                }
            }
        }
    }

    private fun showBalance(wallet: Wallet) {
        binding.valueBalance.text =
            getString(R.string.text_formated_value, GetMask.getFormatedValue(wallet.balance))

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}