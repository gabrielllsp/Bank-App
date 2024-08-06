package com.gabriel.bankapp.presenter.features.deposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gabriel.bankapp.databinding.FragmentDepositFormBinding
import com.gabriel.bankapp.util.initToolbar


class DepositFormFragment : Fragment() {

    private var _binding: FragmentDepositFormBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepositFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar = binding.toolbar, light = true)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}