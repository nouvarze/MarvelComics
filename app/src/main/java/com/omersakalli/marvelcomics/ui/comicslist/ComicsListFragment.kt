package com.omersakalli.marvelcomics.ui.comicslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omersakalli.marvelcomics.R
import com.omersakalli.marvelcomics.databinding.ComicsListFragmentBinding
import com.omersakalli.marvelcomics.ui.model.Comic
import com.omersakalli.marvelcomics.utils.BundleKeys
import com.omersakalli.marvelcomics.utils.LoggingTags
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
        mAdapter = ComicsListAdapter(this)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                initRecyclerView(view.height)
            }
        })

        mBinding.vm = viewModel
        viewModel.fetchComics()
        initViews()
        initObservers()

    }

    private fun initViews() {
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchComicsFromNetwork()
        }
    }

    private fun initObservers() {
        viewModel.comics.observe(viewLifecycleOwner, {
            mAdapter.comicsList = it
            mAdapter.notifyDataSetChanged()
            mBinding.swipeRefreshLayout.isRefreshing = false
        })
        viewModel.progressBarVisibility.observe(viewLifecycleOwner, {
            mBinding.progressBar.visibility = it
        })
        viewModel.navigateToErrorPage.observe(viewLifecycleOwner, {
            findNavController().navigate(
                R.id.action_comicsListFragment_to_comicDetailsFragment,
                Bundle().apply { putString(BundleKeys.ERROR_TEXT, it) }
            )
        })
    }

    private fun initRecyclerView(height: Int) {
        mBinding.rvComicsList.run {
            layoutManager = GridLayoutManager(
                context,
                calculateSpanCount(height),
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = mAdapter
        }
    }

    override fun onComicClick(comic: Comic) {
        findNavController().navigate(
            R.id.action_comicsListFragment_to_comicDetailsFragment,
            Bundle().apply { putParcelable(BundleKeys.COMIC, comic) }
        )
    }

    private fun calculateSpanCount(height: Int): Int {
        val result = height / resources.getDimensionPixelSize(R.dimen.comics_list_item_height)
        Log.d(LoggingTags.FRAGMENT_CALCULATIONS, "Height: $height, Result: $result")

        if (result > 0) return result
        else {
            Log.e(LoggingTags.FRAGMENT_CALCULATIONS, "Error calculating span count, defaulting to 2")
            return 2
        }
    }

}