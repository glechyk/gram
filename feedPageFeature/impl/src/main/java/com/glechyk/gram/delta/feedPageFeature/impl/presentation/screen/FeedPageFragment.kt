package com.glechyk.gram.delta.feedPageFeature.impl.presentation.screen

import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import com.chi.interngram.delta.feedPageFeature.impl.R
import com.chi.interngram.delta.feedPageFeature.impl.databinding.FragmentFeedPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedPageFragment : BaseFragment<FragmentFeedPageBinding, FeedPageViewModel>(
    R.layout.fragment_feed_page
) {
    override val binding by viewBinding({
        FragmentFeedPageBinding.bind(it)
    })

    override val viewModel by viewModel<FeedPageViewModel>()
}
