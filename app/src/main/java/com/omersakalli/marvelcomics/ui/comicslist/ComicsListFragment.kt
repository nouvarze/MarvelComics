package com.omersakalli.marvelcomics.ui.comicslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omersakalli.marvelcomics.R
import com.omersakalli.marvelcomics.databinding.ComicsListFragmentBinding
import com.omersakalli.marvelcomics.ui.GridAutofitLayoutManager
import com.omersakalli.marvelcomics.ui.model.Comic
import com.omersakalli.marvelcomics.utils.BundleKeys
import com.omersakalli.marvelcomics.utils.ImageUtils.extractUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsListFragment : Fragment(), OnComicClickListener {

    private lateinit var mBinding: ComicsListFragmentBinding
    private lateinit var mAdapter: ComicsListAdapter
    private val viewModel: ComicsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.comics_list_fragment, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.vm = viewModel
        viewModel.fetchComics()
        initViews()
        initObservers()

    }

    private fun initViews(){
        initRecyclerView()
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchComicsFromNetwork()
            //TODO: Can add a pop-up when there is no internet to make sure user doesn't lose data
        }
    }

    private fun initObservers(){
        viewModel.comics.observe(viewLifecycleOwner, {
            mAdapter.comicsList = it
            mAdapter.notifyDataSetChanged()
            mBinding.swipeRefreshLayout.isRefreshing = false
        })
        viewModel.progressBarVisibility.observe(viewLifecycleOwner,{
            mBinding.progressBar.visibility = it
        })
        viewModel.navigateToErrorPage.observe(viewLifecycleOwner,{
            findNavController().navigate(
                R.id.action_comicsListFragment_to_comicDetailsFragment,
                Bundle().apply { putString(BundleKeys.ERROR_TEXT, it) }
            )
        })
    }

    private fun initRecyclerView() {
        mAdapter = ComicsListAdapter(this)
        mBinding.rvComicsList.run {
            layoutManager = GridAutofitLayoutManager(context, 300, RecyclerView.HORIZONTAL, false)
            adapter = mAdapter
        }
    }

    override fun onComicClick(comic: Comic) {
        findNavController().navigate(
            R.id.action_comicsListFragment_to_comicDetailsFragment,
            Bundle().apply { putParcelable(BundleKeys.COMIC, comic) }
        )
    }

}