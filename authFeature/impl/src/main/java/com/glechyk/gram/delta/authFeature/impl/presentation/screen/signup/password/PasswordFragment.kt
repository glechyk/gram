package com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.password

import android.util.Log
import com.chi.interngram.delta.authFeature.impl.R
import com.chi.interngram.delta.authFeature.impl.databinding.FragmentPasswordBinding
import com.glechyk.gram.delta.authFeature.impl.presentation.model.PasswordConfirmationUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.PasswordValidationUiState
import com.glechyk.gram.delta.authFeature.impl.utils.PasswordValidator
import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.addOnTextChangedListener
import com.glechyk.gram.delta.core.utils.clickAction
import com.glechyk.gram.delta.core.utils.setTextViewDrawableTint
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PasswordFragment :
    BaseFragment<FragmentPasswordBinding, PasswordViewModel>(R.layout.fragment_password) {

    override val binding by viewBinding({
        FragmentPasswordBinding.bind(it)
    })

    override val viewModel by viewModel<PasswordViewModel>()

    override fun setUpFragmentScope() {
        val authFragmentScope = parentOfNavHostFragment.scope
        scope.linkTo(authFragmentScope)
    }

    override fun setUpView() {
        setUpButtons()
        setUpEditTexts()
    }

    override fun observeViewModel() {
        with(viewModel) {
            validationUiState.observe { uiState ->
                handleValidationUiState(uiState)
            }

            confirmationUiState.observe { uiState ->
                handleConfirmationUiState(uiState)
            }

            passwordFlow.observe { cachedPassword ->
                handlePasswordInput(cachedPassword)
            }
        }
    }

    private fun setUpButtons() {
        with(binding) {
            nextButton.clickAction {
                savePassword(binding.confirmPasswordEditText.text.toString().trim())
                goToNicknameScreen()
            }

            backButton.clickAction {
                savePassword(null)
                pop()
            }
        }
    }

    private fun setUpEditTexts() {
        with(binding) {
            enterPasswordEditText.addOnTextChangedListener {
                validatePassword(it)
            }

            confirmPasswordEditText.addOnTextChangedListener {
                validatePasswordConfirmation(enterPasswordEditText.text.toString(), it)
            }
        }
    }

    private fun validatePassword(password: String) {
        viewModel.validatePassword(password)
    }

    private fun validatePasswordConfirmation(password: String, confirmation: String) {
        viewModel.validatePasswordConfirmation(password, confirmation)
    }

    private fun savePassword(password: String?) {
        viewModel.savePassword(password)
    }

    private fun validationEmptyUiState() {
        with(binding) {
            //TextView
            minCharTextView.setTextColor(getTextColor(false))
            uppercaseTextView.setTextColor(getTextColor(false))
            lowercaseTextView.setTextColor(getTextColor(false))
            digitTextView.setTextColor(getTextColor(false))
            specCharTextView.setTextColor(getTextColor(false))

            //TextViewDrawable
            minCharTextView.setTextViewDrawableTint(getDrawableColor(false))
            uppercaseTextView.setTextViewDrawableTint(getDrawableColor(false))
            lowercaseTextView.setTextViewDrawableTint(getDrawableColor(false))
            digitTextView.setTextViewDrawableTint(getDrawableColor(false))
            specCharTextView.setTextViewDrawableTint(getDrawableColor(false))

            //Error
            enterPasswordInputLayout.isErrorEnabled = false
        }
    }

    private fun validationUiState(validator: PasswordValidator) {
        with(binding) {
            with(validator) {
                //TextView
                minCharTextView.setTextColor(getTextColor(minEightChar))
                uppercaseTextView.setTextColor(getTextColor(minOneUpperCase))
                lowercaseTextView.setTextColor(getTextColor(minOneLowerCase))
                digitTextView.setTextColor(getTextColor(minOneDigit))
                specCharTextView.setTextColor(getTextColor(minOneSpecChar))

                //TextViewDrawable
                minCharTextView.setTextViewDrawableTint(getDrawableColor(minEightChar))
                uppercaseTextView.setTextViewDrawableTint(getDrawableColor(minOneUpperCase))
                lowercaseTextView.setTextViewDrawableTint(getDrawableColor(minOneLowerCase))
                digitTextView.setTextViewDrawableTint(getDrawableColor(minOneDigit))
                specCharTextView.setTextViewDrawableTint(getDrawableColor(minOneSpecChar))

                //Error
                enterPasswordInputLayout.error =
                    getString(R.string.validation_error_password_enter)
                enterPasswordInputLayout.isErrorEnabled = !validator.isSuccess
            }
        }
    }

    private fun confirmationUiState(
        isButtonEnabled: Boolean = false,
        isErrorEnabled: Boolean = false
    ) {
        with(binding) {
            nextButton.isEnabled = isButtonEnabled
            confirmPasswordInputLayout.error =
                getString(R.string.validation_error_password_confirm)
            confirmPasswordInputLayout.isErrorEnabled = isErrorEnabled
        }
    }

    private fun getTextColor(condition: Boolean) = requireContext().getColor(
        if (condition) com.chi.interngram.delta.uikit.R.color.black_65
        else com.chi.interngram.delta.uikit.R.color.black_45
    )

    private fun getDrawableColor(condition: Boolean) = requireContext().getColor(
        if (condition) com.chi.interngram.delta.uikit.R.color.success_green
        else com.chi.interngram.delta.uikit.R.color.white
    )

    private fun handleValidationUiState(uiState: PasswordValidationUiState) {
        when (uiState) {
            is PasswordValidationUiState.Empty -> {
                Log.d(TAG, "validationUiState() -> Empty")
                validationEmptyUiState()
                binding.nextButton.isEnabled = false
            }
            is PasswordValidationUiState.Success -> {
                Log.d(TAG, "validationUiState() -> Success")
                validationUiState(uiState.validator)
            }
            is PasswordValidationUiState.Error -> {
                Log.d(TAG, "validationUiState() -> Error")
                validationUiState(uiState.validator)
                binding.nextButton.isEnabled = false
            }
        }
    }

    private fun handleConfirmationUiState(uiState: PasswordConfirmationUiState) {
        when (uiState) {
            is PasswordConfirmationUiState.Empty -> {
                Log.d(TAG, "confirmationUiState() -> Empty")
                confirmationUiState()
            }
            is PasswordConfirmationUiState.Success -> {
                Log.d(TAG, "confirmationUiState() -> Success")
                binding.nextButton.isLoading(false)
                confirmationUiState(
                    isButtonEnabled = true
                )
            }
            is PasswordConfirmationUiState.Error -> {
                Log.d(TAG, "confirmationUiState() -> Error")
                confirmationUiState(
                    isErrorEnabled = true
                )
            }
        }
    }

    private fun handlePasswordInput(cachedPassword: String) {
        with(binding) {
            enterPasswordEditText.setText(cachedPassword)
            confirmPasswordEditText.setText(cachedPassword)
        }
    }

    private fun goToNicknameScreen() =
        PasswordFragmentDirections.actionPasswordFragmentToNicknameFragment().go()

    companion object {
        private val TAG = PasswordFragment::class.simpleName
    }
}
