package com.omersakalli.marvelcomics.ui.errorscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.omersakalli.marvelcomics.R
import com.omersakalli.marvelcomics.databinding.ErrorFragmentBinding
import com.omersakalli.marvelcomics.utils.BundleKeys

class ErrorFragment : Fragment() {

    private val viewModel: ErrorViewModel by viewModels()
    private lateinit var mBinding: ErrorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.error_fragment, container, false)
        mBinding.vm = viewModel
        initArguments()
        return mBinding.root
    }

    private fun initArguments(){
        arguments?.getString(BundleKeys.ERROR_TEXT)?.let {
            viewModel.errorText.value = it
        } ?: kotlin.run {
            viewModel.errorText.value = "Unknown Error"
        }
    }

}