package com.gabriel.bankapp.presenter.auth.recover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gabriel.bankapp.R
import com.gabriel.bankapp.databinding.FragmentRecoverBinding
import com.gabriel.bankapp.util.FirebaseHelper
import com.gabriel.bankapp.util.StateView
import com.gabriel.bankapp.util.initToolbar
import com.gabriel.bankapp.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoverFragment : Fragment() {
    private var _binding: FragmentRecoverBinding? = null
    private val binding get() = _binding!!

    private val recoverViewModel: RecoverViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar = binding.toolbar, light = false)
        initListener()
    }

    private fun initListener() {
        binding.btnForgot.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = binding.editEmail.text.toString().trim()

        if (email.isNotEmpty()) {
            recoverUser(email)
        } else {
            showBottomSheet(message = getString(R.string.text_email_empty))
        }
    }

    private fun recoverUser(email: String) {
        recoverViewModel.recover(email).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.progressLoading.isVisible = true
                }

                is StateView.Sucess -> {
                    binding.progressLoading.isVisible = false

                    showBottomSheet(message = getString(R.string.text_send_email_success_recover_fragment))
                }

                is StateView.Error -> {
                    binding.progressLoading.isVisible = false

                    showBottomSheet(
                        message = getString(
                            FirebaseHelper.validError(
                                stateView.message ?: ""
                            )
                        )
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}