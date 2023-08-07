package com.glechyk.gram.delta.mainFeature.impl.presentation.screen

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import com.chi.interngram.delta.mainFeature.impl.R
import com.chi.interngram.delta.mainFeature.impl.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(
    R.layout.fragment_main
) {

    override val binding by viewBinding({
        FragmentMainBinding.bind(it)
    })

    override val viewModel by viewModel<MainViewModel>()

    override fun setUpView() {
        val navController =
            binding.mainNavHostFragment.getFragment<NavHostFragment>().navController
        binding.mainBottomNavigationView.setupWithNavController(navController)
    }
}
