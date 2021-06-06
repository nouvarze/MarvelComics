package com.omersakalli.marvelcomics.ui.comicsdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.omersakalli.marvelcomics.R
import com.omersakalli.marvelcomics.databinding.FragmentComicDetailBottomSheetBinding
import com.omersakalli.marvelcomics.utils.StringUtils.htmlToSpanned

class ComicDetailBottomSheetFragment(private val viewModel: ComicDetailsViewModel) : BottomSheetDialogFragment() {

    private lateinit var mBinding: FragmentComicDetailBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_comic_detail_bottom_sheet, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers(){
        viewModel.comic.observe(viewLifecycleOwner,{ comic->
            comic.description?.let { str->
                mBinding.tvDescription.text = str.htmlToSpanned()
            }
            comic.title?.let {  str->
                mBinding.tvTitle.text = str.htmlToSpanned()
            }
        })
    }
}