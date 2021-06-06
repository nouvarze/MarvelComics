package com.omersakalli.marvelcomics.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.omersakalli.marvelcomics.R
import com.omersakalli.marvelcomics.databinding.SplashFragmentBinding

class SplashFragment : Fragment() {
    private lateinit var mBinding: SplashFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.splash_fragment, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkRequirements()
    }

    private fun checkRequirements(){

        //TODO: check permissions
        //TODO: if there is no internet connection & no data on database redirect to error page

        findNavController().navigate(R.id.action_splashFragment_to_comicsListFragment)
    }
}