package com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.fullName

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.chi.interngram.delta.authFeature.impl.R
import com.chi.interngram.delta.authFeature.impl.databinding.FragmentFullNameBinding
import com.glechyk.gram.delta.authFeature.impl.presentation.model.FullNameUiState
import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.addOnTextChangedListener
import com.glechyk.gram.delta.core.utils.asString
import com.glechyk.gram.delta.core.utils.clickAction
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FullNameFragment : BaseFragment<FragmentFullNameBinding, FullNameViewModel>(
    R.layout.fragment_full_name
) {

    override val binding by viewBinding({
        FragmentFullNameBinding.bind(it)
    })

    override val viewModel by viewModel<FullNameViewModel>()

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

            fullNameFlow.observe {
                binding.fullNameTextInputEditText.setText(it)
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
            with(fullNameTextInputLayout) {
                isErrorEnabled = isErrorTextInputLayoutEnabled
                if (isErrorEnabled) error = errorMessageTextInputLayout
                endIconMode = com.google.android.material.textfield.TextInputLayout.END_ICON_CUSTOM
                endIconDrawable = successIconDrawableTextInputLayout
            }
            progressButton.isEnabled = isButtonEnabled
        }
    }

    private fun setUpButtons() {
        with(binding) {
            progressButton.clickAction {
                viewModel.saveFullName(fullNameTextInputEditText.text.toString().trim())
                goToBirthdayScreen()
            }
            backButton.clickAction {
                viewModel.saveFullName(null)
                pop()
            }
        }
    }

    private fun setUpTextInput() {
        binding.fullNameTextInputEditText.addOnTextChangedListener { string ->
            viewModel.validate(string.trim())
        }
    }

    private fun handleUiState(uiState: FullNameUiState) {
        when (uiState) {
            is FullNameUiState.Empty -> uiState(
                isErrorTextInputLayoutEnabled = false,
                isButtonEnabled = false
            )
            is FullNameUiState.Error -> uiState(
                isErrorTextInputLayoutEnabled = true,
                errorMessageTextInputLayout = uiState.uiText.asString(requireContext()),
                isButtonEnabled = false
            )
            is FullNameUiState.Success -> uiState(
                isErrorTextInputLayoutEnabled = false,
                successIconDrawableTextInputLayout = ContextCompat.getDrawable(
                    requireContext(),
                    com.chi.interngram.delta.uikit.R.drawable.ic_end_success
                ),
                isButtonEnabled = true
            )
        }
    }

    private fun goToBirthdayScreen() =
        FullNameFragmentDirections.actionFullNameFragmentToBirthdayFragment().go()

}
