package com.gabriel.bankapp.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gabriel.bankapp.R
import com.gabriel.bankapp.databinding.LayoutBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Fragment.initToolbar(
    toolbar: androidx.appcompat.widget.Toolbar,
    homeAsUpEnabled: Boolean = true
) {
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title = ""
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(homeAsUpEnabled)
    (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
}

fun Fragment.showBottomSheet(
    titleDialog: Int? = null,
    titleButton: Int? = null,
    message: String,
    onClick: () -> Unit = {}
) {
    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
    val bottomSheetBinging: LayoutBottomSheetBinding =
        LayoutBottomSheetBinding.inflate(layoutInflater, null, false)

    bottomSheetBinging.textTitle.text = getString(titleDialog ?: R.string.text_title_bottom_sheet)
    bottomSheetBinging.textMessage.text = message
    bottomSheetBinging.btnOk.text = getString(titleButton ?: R.string.text_button_bottom_sheet)

    bottomSheetBinging.btnOk.setOnClickListener {
        bottomSheetDialog.dismiss()
        onClick() }

    bottomSheetDialog.setContentView(bottomSheetBinging.root)
    bottomSheetDialog.show()
}