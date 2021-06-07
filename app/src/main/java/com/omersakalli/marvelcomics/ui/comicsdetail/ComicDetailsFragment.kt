package com.omersakalli.marvelcomics.ui.comicsdetail

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.omersakalli.marvelcomics.R
import com.omersakalli.marvelcomics.data.model.Thumbnail
import com.omersakalli.marvelcomics.databinding.ComicsDetailFragmentBinding
import com.omersakalli.marvelcomics.ui.model.Comic
import com.omersakalli.marvelcomics.utils.BundleKeys
import com.omersakalli.marvelcomics.utils.ImageUtils
import com.omersakalli.marvelcomics.utils.ImageUtils.extractUrl
import com.omersakalli.marvelcomics.utils.StringUtils.httpToHttps

class ComicDetailsFragment : Fragment() {

    private lateinit var mBinding: ComicsDetailFragmentBinding
    private val viewModel: ComicDetailsViewModel by viewModels()
    private lateinit var comicDetailBottomSheetFragment: ComicDetailBottomSheetFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.comics_detail_fragment, container, false)
        (arguments?.getParcelable(BundleKeys.COMIC) as Comic?)?.let {
            viewModel.comic.value = it
        } ?: kotlin.run {
            findNavController().navigate(
                R.id.action_comicDetailsFragment_to_errorFragment,
                Bundle().apply { putString(BundleKeys.ERROR_TEXT, "Error while opening comic details") }
            )
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.vm = viewModel
        comicDetailBottomSheetFragment = ComicDetailBottomSheetFragment(viewModel)
        mBinding.ivBackButton.setOnClickListener { activity?.onBackPressed() }
        mBinding.ivOpenDetailsButton.setOnClickListener {
            comicDetailBottomSheetFragment.show(parentFragmentManager, "COMIC_DETAIL_BOTTOM_SHEET")
        }
        mBinding.tvOpenDetails.setOnClickListener {
            comicDetailBottomSheetFragment.show(parentFragmentManager, "COMIC_DETAIL_BOTTOM_SHEET")
        }
        viewModel.comic.observe(viewLifecycleOwner, {
            loadCoverImage(it.thumbnail)
        })
    }

    private fun loadCoverImage(thumbnail: Thumbnail?) {
        val orientation = resources.configuration.orientation

        thumbnail?.let { _ ->
            val url = if (orientation == Configuration.ORIENTATION_PORTRAIT)
                thumbnail.extractUrl().httpToHttps()
            else
                thumbnail.extractUrl(ImageUtils.AspectRatio.LANDSCAPE, true).httpToHttps()

            Glide.with(mBinding.root)
                .load(url)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(mBinding.ivCoverArt)
        } ?: kotlin.run {
            Glide.with(mBinding.root)
                .load(R.drawable.image_not_available)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(mBinding.ivCoverArt)
        }

    }
}