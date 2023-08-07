package com.glechyk.gram.delta.searchPageFeature.impl.presentation.screen

import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import com.chi.interngram.delta.searchPageFeature.impl.R
import com.chi.interngram.delta.searchPageFeature.impl.databinding.FragmentSearchPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchPageFragment : BaseFragment<FragmentSearchPageBinding, SearchPageViewModel>(
    R.layout.fragment_search_page
) {
    override val binding by viewBinding({
        FragmentSearchPageBinding.bind(it)
    })

    override val viewModel by viewModel<SearchPageViewModel>()
}
