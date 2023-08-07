package com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.nickname

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat
import com.chi.interngram.delta.authFeature.impl.R
import com.chi.interngram.delta.authFeature.impl.databinding.FragmentNicknameBinding
import com.glechyk.gram.delta.authFeature.impl.presentation.model.NicknameUiState
import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.addOnTextChangedListener
import com.glechyk.gram.delta.core.utils.asString
import com.glechyk.gram.delta.core.utils.clickAction
import com.glechyk.gram.delta.core.utils.showShortToast
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import com.google.android.material.textfield.TextInputLayout.END_ICON_CUSTOM
import org.koin.androidx.viewmodel.ext.android.viewModel

class NicknameFragment : BaseFragment<FragmentNicknameBinding, NicknameViewModel>(
    R.layout.fragment_nickname
) {

    override val binding by viewBinding({
        FragmentNicknameBinding.bind(it)
    })

    override val viewModel by viewModel<NicknameViewModel>()

    override fun setUpFragmentScope() {
        val authFragmentScope = parentOfNavHostFragment.scope
        scope.linkTo(authFragmentScope)
    }

    override fun setUpView() {
        setUpTextInput()
        setUpButtons()
    }

    override fun observeViewModel() {
        with(viewModel) {
            uiState.observe { uiState ->
                handleUiState(uiState)
            }
            nicknameFlow.observe {
                binding.nicknameTextInputEditText.setText(it)
            }
        }
    }

    private fun uiState(
        isErrorTextInputLayoutEnabled: Boolean,
        errorMessageTextInputLayout: String? = null,
        successIconDrawableTextInputLayout: Drawable? = null,
        isButtonEnabled: Boolean
    ) {
        with(binding) {
            with(nicknameTextInputLayout) {
                isErrorEnabled = isErrorTextInputLayoutEnabled
                if (isErrorEnabled) error = errorMessageTextInputLayout
                endIconMode = END_ICON_CUSTOM
                endIconDrawable = successIconDrawableTextInputLayout
            }
            progressButton.isEnabled = isButtonEnabled
        }
    }

    private fun setUpButtons() {
        with(binding) {
            progressButton.clickAction {
                viewModel.saveNickname(nicknameTextInputEditText.text.toString().trim())
                goToFullNameScreen()
            }
            backButton.clickAction {
                viewModel.saveNickname(null)
                pop()
            }
        }
    }

    private fun setUpTextInput() {
        binding.nicknameTextInputEditText.addOnTextChangedListener { string ->
            viewModel.validate(string.trim())
        }
    }

    private fun handleUiState(uiState: NicknameUiState) {
        when (uiState) {
            is NicknameUiState.Empty -> uiState(
                isErrorTextInputLayoutEnabled = false,
                isButtonEnabled = false
            )
            is NicknameUiState.Error -> uiState(
                isErrorTextInputLayoutEnabled = true,
                errorMessageTextInputLayout = uiState.uiText.asString(requireContext()),
                isButtonEnabled = false
            )
            is NicknameUiState.Success -> uiState(
                isErrorTextInputLayoutEnabled = false,
                successIconDrawableTextInputLayout = ContextCompat.getDrawable(
                    requireContext(),
                    com.chi.interngram.delta.uikit.R.drawable.ic_end_success
                ),
                isButtonEnabled = true
            )
            is NicknameUiState.ServerError -> {
                requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.server_error))
            }
            is NicknameUiState.NoInternetError -> {
                requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.no_internet_connection))
                uiState(
                    isErrorTextInputLayoutEnabled = false,
                    isButtonEnabled = false
                )
            }
            is NicknameUiState.UnknownError -> {
                Log.d(TAG, uiState.message)
                uiState(
                    isErrorTextInputLayoutEnabled = false,
                    isButtonEnabled = false
                )
            }
        }
    }

    private fun goToFullNameScreen() =
        NicknameFragmentDirections.actionNicknameFragmentToFullNameFragment().go()

    companion object {
        private val TAG = NicknameFragment::class.simpleName
    }
}
