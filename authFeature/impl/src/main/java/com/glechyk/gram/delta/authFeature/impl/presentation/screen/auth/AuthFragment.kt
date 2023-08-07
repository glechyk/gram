package com.glechyk.gram.delta.authFeature.impl.presentation.screen.auth

import com.chi.interngram.delta.authFeature.impl.R
import com.chi.interngram.delta.authFeature.impl.databinding.FragmentAuthBinding
import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : BaseFragment<FragmentAuthBinding, AuthViewModel>(
    R.layout.fragment_auth
) {

    override val binding by viewBinding({
        FragmentAuthBinding.bind(it)
    })

    override val viewModel by viewModel<AuthViewModel>()

}
