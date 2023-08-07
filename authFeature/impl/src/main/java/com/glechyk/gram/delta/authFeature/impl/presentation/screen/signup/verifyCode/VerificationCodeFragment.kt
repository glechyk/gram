package com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.verifyCode

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.navigation.fragment.findNavController
import com.chi.interngram.delta.authFeature.impl.R
import com.chi.interngram.delta.authFeature.impl.databinding.FragmentVerificationCodeBinding
import com.glechyk.gram.delta.authFeature.impl.presentation.model.SendingVerificationCodeUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.TimerUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.ConfirmationVerificationCodeUiState
import com.glechyk.gram.delta.authFeature.impl.presentation.model.SignUpUiState
import com.glechyk.gram.delta.authFeature.impl.utils.UserCachedLogin
import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.Constants
import com.glechyk.gram.delta.core.utils.addOnTextChangedListener
import com.glechyk.gram.delta.core.utils.asString
import com.glechyk.gram.delta.core.utils.clickAction
import com.glechyk.gram.delta.core.utils.showShortToast
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import com.glechyk.gram.delta.mainFeature.api.MainFeatureNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class VerificationCodeFragment :
    BaseFragment<FragmentVerificationCodeBinding, VerificationCodeViewModel>(
        R.layout.fragment_verification_code
    ) {

    override val binding by viewBinding({
        FragmentVerificationCodeBinding.bind(it)
    })

    override val viewModel by viewModel<VerificationCodeViewModel>()

    private val mainFeatureNavigator by inject<MainFeatureNavigator>()

    private val formatter = SimpleDateFormat("mm:ss", Locale.US)

    override fun setUpFragmentScope() {
        val authFragmentScope = parentOfNavHostFragment.scope
        scope.linkTo(authFragmentScope)
    }

    override fun setUpView() {
        setUpTextViews()
        setUpButtons()
        setUpTextInput()
    }

    override fun observeViewModel() {
        with(viewModel) {
            sendingState.observe { sendingCodeState ->
                handleSendingCodeUiState(uiState = sendingCodeState)
            }
            confirmationState.observe { confirmationCodeUiState ->
                handleConfirmationCodeUiState(uiState = confirmationCodeUiState)
            }
            signUpState.observe { signUpUiState ->
                handleSignUpUiState(uiState = signUpUiState)
            }
            timerFlow.observe { timerUiState ->
                handleTimerUiState(uiState = timerUiState)
            }
        }
    }

    private fun setUpHintTextView(
        @StringRes stringId: Int = R.string.verify_code_screen_hint,
        @ColorRes colorId: Int = com.chi.interngram.delta.uikit.R.color.black
    ) {
        with(binding) {
            hintTextView.text = requireContext().getString(stringId)
            hintTextView.setTextColor(ContextCompat.getColor(requireContext(), colorId))
        }
    }

    private fun setUpPhoneEmailTextViews(
        @StringRes descriptionStringId: Int,
        @StringRes changeStringId: Int,
    ) {
        with(binding) {
            descriptionTextView.text =
                requireContext().getString(descriptionStringId)
            changeTextView.text =
                requireContext().getString(changeStringId)
        }
    }

    private fun setUpTextViews() {
        with(binding) {
            when (viewModel.getUserCachedLogin()) {
                UserCachedLogin.Phone -> {
                    setUpPhoneEmailTextViews(
                        descriptionStringId = R.string.verify_code_screen_description_phone,
                        changeStringId = R.string.verify_code_screen_change_phone
                    )
                }
                UserCachedLogin.Email -> {
                    setUpPhoneEmailTextViews(
                        descriptionStringId = R.string.verify_code_screen_description_email,
                        changeStringId = R.string.verify_code_screen_change_email
                    )
                }
                UserCachedLogin.Nothing -> {
                    descriptionTextView.text = com.glechyk.gram.delta.core.utils.Constants.EMPTY_STRING
                    changeTextView.text = com.glechyk.gram.delta.core.utils.Constants.EMPTY_STRING
                }
            }
            descriptionTextView.isInvisible = changeTextView.text.isBlank()
            changeTextView.isInvisible = changeTextView.text.isBlank()
            codeTextView.text = viewModel.userInputToCode(com.glechyk.gram.delta.core.utils.Constants.EMPTY_STRING)
        }
    }

    private fun setUpButtons() {
        with(binding) {
            progressButton.isEnabled = false
            resendTextView.clickAction {
                viewModel.sendVerificationCode()
            }
            changeTextView.clickAction {
                VerificationCodeFragmentDirections.actionVerifyCodeFragmentToPhoneEmailFragment()
                    .go()
            }
            progressButton.clickAction {
                viewModel.confirmCode(codeTextInputEditText.text.toString())
            }
            backButton.clickAction {
                pop()
            }
        }
    }

    private fun setUpTextInput() {
        with(binding) {
            codeTextInputEditText.addOnTextChangedListener { string ->
                codeTextInputLayout.isErrorEnabled = false
                codeTextView.text = viewModel.userInputToCode(string)
                progressButton.isEnabled = string.length == 6
            }
        }
    }

    private fun timerState(isTimerInvisible: Boolean, isEnabledResend: Boolean) {
        with(binding) {
            timerTextView.isInvisible = isTimerInvisible
            resendTextView.isEnabled = isEnabledResend
        }
    }

    private fun handleSendingCodeUiState(uiState: SendingVerificationCodeUiState) {
        when (uiState) {
            is SendingVerificationCodeUiState.Success -> {
                Log.d(TAG, "Verification code was sent")
            }
            is SendingVerificationCodeUiState.NoInternetError -> {
                requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.no_internet_connection))
            }
            is SendingVerificationCodeUiState.ServerError -> {
                requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.server_error))
            }
            is SendingVerificationCodeUiState.RequestLimitError -> {
                viewModel.startTimer(uiState.time)
            }
            is SendingVerificationCodeUiState.UnknownError -> {
                Log.d(TAG, "SendingCodeUiState UnknownError: ${uiState.message}")
            }
        }
    }

    private fun handleConfirmationCodeUiState(uiState: ConfirmationVerificationCodeUiState) {
        when (uiState) {
            is ConfirmationVerificationCodeUiState.Success -> {
                viewModel.signUpUser()
            }
            is ConfirmationVerificationCodeUiState.Error -> {
                with(binding) {
                    codeTextInputLayout.error =
                        uiState.uiText.asString(requireContext())
                    progressButton.isLoading(false)
                    progressButton.isEnabled = false
                }
            }
            is ConfirmationVerificationCodeUiState.NoInternetError -> {
                requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.no_internet_connection))
            }
            is ConfirmationVerificationCodeUiState.ServerError -> {
                requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.server_error))
            }
            is ConfirmationVerificationCodeUiState.RequestLimitError -> {
                viewModel.startTimer(uiState.time)
            }
            is ConfirmationVerificationCodeUiState.UnknownError -> {
                Log.d(TAG, "ConfirmationCodeUiState UnknownError: ${uiState.message}")
            }
        }
    }

    private fun handleSignUpUiState(uiState: SignUpUiState) {
        when (uiState) {
            is SignUpUiState.Success -> {
                requireContext().showShortToast("User was signed up successfully")
                parentOfNavHostFragment.findNavController().navigate(mainFeatureNavigator.mainFeatureDeepLink)
            }
            is SignUpUiState.NoInternetError -> {
                requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.no_internet_connection))
            }
            is SignUpUiState.ServerError -> {
                requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.server_error))
            }
            is SignUpUiState.UnknownError -> {
                Log.d(TAG, "SignUpUiState UnknownError: ${uiState.message}")
            }
        }
    }

    private fun handleTimerUiState(uiState: TimerUiState) {
        when (uiState) {
            is TimerUiState.Start -> {
                binding.codeTextInputEditText.text = null
                timerState(
                    isTimerInvisible = false,
                    isEnabledResend = false
                )
                if (uiState.timeOfTimer != Constants.MINUTE_IN_MILLIS) {
                    setUpHintTextView(
                        stringId = R.string.verify_code_screen_resend_error,
                        colorId = com.chi.interngram.delta.uikit.R.color.error_red
                    )
                }
            }
            is TimerUiState.Tick -> {
                timerState(
                    isTimerInvisible = false,
                    isEnabledResend = false
                )
                binding.timerTextView.text =
                    formatter.format(uiState.millisUntilFinished)
            }
            is TimerUiState.Finish -> {
                timerState(
                    isTimerInvisible = true,
                    isEnabledResend = true
                )
                setUpHintTextView()
            }
        }
    }

    companion object {
        private val TAG = VerificationCodeFragment::class.simpleName
    }
}
