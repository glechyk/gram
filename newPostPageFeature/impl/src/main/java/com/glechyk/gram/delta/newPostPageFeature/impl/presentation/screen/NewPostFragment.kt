package com.glechyk.gram.delta.newPostPageFeature.impl.presentation.screen

import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import com.chi.interngram.delta.newPostPageFeature.impl.R
import com.chi.interngram.delta.newPostPageFeature.impl.databinding.FragmentNewPostBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewPostFragment : BaseFragment<FragmentNewPostBinding, NewPostViewModel>(
    R.layout.fragment_new_post
) {
    override val binding by viewBinding({
        FragmentNewPostBinding.bind(it)
    })

    override val viewModel by viewModel<NewPostViewModel>()
}
