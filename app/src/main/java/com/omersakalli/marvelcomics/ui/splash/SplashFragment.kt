package com.omersakalli.marvelcomics.ui.splash

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.omersakalli.marvelcomics.R
import com.omersakalli.marvelcomics.data.Repository
import com.omersakalli.marvelcomics.databinding.SplashFragmentBinding
import com.omersakalli.marvelcomics.utils.BundleKeys
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var mBinding: SplashFragmentBinding
    private val viewModel: SplashViewModel by viewModels()

    @Inject
    lateinit var repository: Repository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.splash_fragment, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        checkRequirements()
    }

    private fun initObservers() {
        viewModel.isDataBaseEmpty.observe(viewLifecycleOwner,{
            if(it)
                showConnectionError()
            else
                navigateToListFragment()
        })
    }

    private fun checkRequirements(){
        if(!isOnline()){
            viewModel.checkDatabase()
        } else
            navigateToListFragment()
    }

    private fun isOnline(): Boolean {
        val connMgr = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

    private fun showConnectionError(){
        findNavController().navigate(
            R.id.action_splashFragment_to_errorFragment,
            Bundle().apply { putString(BundleKeys.ERROR_TEXT, "You need an internet connection to fetch comics list") }
        )
    }

    private fun navigateToListFragment(){
        findNavController().navigate(R.id.action_splashFragment_to_comicsListFragment)
    }
}