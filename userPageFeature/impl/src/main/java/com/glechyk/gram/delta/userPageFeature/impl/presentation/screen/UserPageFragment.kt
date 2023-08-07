package com.glechyk.gram.delta.userPageFeature.impl.presentation.screen

import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import com.chi.interngram.delta.userPageFeature.impl.R
import com.chi.interngram.delta.userPageFeature.impl.databinding.FragmentUserPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserPageFragment : BaseFragment<FragmentUserPageBinding, UserPageViewModel>(
    R.layout.fragment_user_page
) {
    override val binding by viewBinding({
        FragmentUserPageBinding.bind(it)
    })

    override val viewModel by viewModel<UserPageViewModel>()
}
