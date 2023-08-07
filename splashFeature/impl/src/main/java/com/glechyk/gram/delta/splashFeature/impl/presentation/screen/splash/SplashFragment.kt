package com.glechyk.gram.delta.splashFeature.impl.presentation.screen.splash

import android.os.Build
import android.util.Log
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import com.glechyk.gram.delta.authFeature.api.AuthFeatureNavigator
import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import com.glechyk.gram.delta.mainFeature.api.MainFeatureNavigator
import com.chi.interngram.delta.splashFeature.impl.R
import com.chi.interngram.delta.splashFeature.impl.databinding.FragmentSplashBinding
import com.glechyk.gram.delta.splashFeature.impl.presentation.model.AuthenticationPresentation
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(
    R.layout.fragment_splash
) {

    override val binding by viewBinding({
        FragmentSplashBinding.bind(it)
    })

    override val viewModel by viewModel<SplashViewModel>()

    private val authFeatureNavigator by inject<AuthFeatureNavigator>()

    private val mainFeatureNavigator by inject<MainFeatureNavigator>()

    override fun setUpView() {
        isStatusBarVisible(false)
    }

    override fun observeViewModel() {
        with(viewModel) {
            authentication.observe {
                delay(2000)
                when (it) {
                    is AuthenticationPresentation.UserIsRegistered -> Log.d(
                        "GG",
                        "User is registered"
                    )
                    is AuthenticationPresentation.UserIsNotRegistered -> findNavController().navigate(
                        authFeatureNavigator.authFeatureDeepLink
//                        mainFeatureNavigator.mainFeatureDeepLink
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isStatusBarVisible(true)
    }

    private fun isStatusBarVisible(status: Boolean) {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            when (status) {
                true -> requireActivity().window.insetsController?.show(WindowInsets.Type.statusBars())
                false -> requireActivity().window.insetsController?.let {
                    it.hide(WindowInsets.Type.statusBars())
                    it.systemBarsBehavior =
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
            }
        } else {
            when (status) {
                true -> requireActivity().window.clearFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
                false -> requireActivity().window.addFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        }
    }
}
