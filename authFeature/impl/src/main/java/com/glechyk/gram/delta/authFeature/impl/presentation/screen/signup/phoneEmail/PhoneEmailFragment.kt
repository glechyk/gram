package com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.phoneEmail

import android.util.Log
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.chi.interngram.delta.authFeature.impl.R
import com.chi.interngram.delta.authFeature.impl.databinding.FragmentPhoneEmailBinding
import com.glechyk.gram.delta.authFeature.impl.presentation.model.PhoneEmailUiState
import com.glechyk.gram.delta.authFeature.impl.utils.Tab
import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.addOnTextChangedListener
import com.glechyk.gram.delta.core.utils.asString
import com.glechyk.gram.delta.core.utils.clickAction
import com.glechyk.gram.delta.core.utils.hideKeyboard
import com.glechyk.gram.delta.core.utils.showShortToast
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhoneEmailFragment :
    BaseFragment<FragmentPhoneEmailBinding, PhoneEmailViewModel>(R.layout.fragment_phone_email) {

    override val binding by viewBinding({
        FragmentPhoneEmailBinding.bind(it)
    })

    override val viewModel by viewModel<PhoneEmailViewModel>()

    override fun setUpFragmentScope() {
        val authFragmentScope = parentOfNavHostFragment.scope
        scope.linkTo(authFragmentScope)
    }

    override fun setUpView() {
        setUpTabLayout()
        setUpEditTexts()
        setUpTextViews()
        setUpButtons()
    }

    override fun observeViewModel() {
        with(viewModel) {
            uiState.observe { uiState ->
                handleUiState(uiState)
            }

            phoneNumberFlow.observe {
                binding.phoneNumberEditText.setText(it)
            }

            emailFlow.observe {
                binding.emailEditText.setText(it)
            }
        }
    }

    private fun setUpTabLayout() {
        with(binding) {
            tabLayout.apply {
                addTab(newTab().setText(context.getString(R.string.phone_email_screen_phone_tab)))
                addTab(newTab().setText(context.getString(R.string.phone_email_screen_email_tab)))
            }

            tabLayout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        Tab.Phone.item -> onPhoneTabSelected(phoneNumberEditText.text.toString())
                        Tab.Email.item -> onEmailTabSelected(emailEditText.text.toString())
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    phoneNumberInputLayout.isErrorEnabled = false
                    emailInputLayout.isErrorEnabled = false
                }
            })

            tabLayout.getTabAt(viewModel.getCurrentTab().item)?.select()
        }
    }

    private fun setUpEditTexts() {
        with(binding) {
            phoneNumberEditText.addOnTextChangedListener {
                validateLocalPhoneNumber(it)
            }

            emailEditText.addOnTextChangedListener {
                validateLocalEmail(it)
            }

            phoneNumberInputLayout.setErrorIconOnClickListener {
                phoneNumberEditText.text?.clear()
            }

            emailInputLayout.setErrorIconOnClickListener {
                emailEditText.text?.clear()
            }
        }
    }

    private fun setUpTextViews() {
        with(binding) {
            termsTextView.clickAction {
                context?.showShortToast(getString(R.string.phone_email_screen_terms_and_conditions))
            }

            loginTextView.clickAction {
                goToLoginScreen()
            }
        }
    }

    private fun setUpButtons() {
        with(binding) {
            nextButton.clickAction {
                with(binding) {
                    when (tabLayout.selectedTabPosition) {
                        Tab.Phone.item -> validateNetworkPhoneNumber(phoneNumberEditText.text.toString())
                        Tab.Email.item -> validateNetworkEmail(emailEditText.text.toString())
                    }
                }
            }

            backButton.clickAction {
                binding.nextButton.isLoading(false)
                goToLoginScreen()
            }
        }
    }

    /** Tab events handling */
    private fun onPhoneTabSelected(phoneNumber: String) {
        hideKeyboard(requireActivity())
        changeButtonConstraint(R.id.phoneNumberInputLayout)
        with(binding) {
            emailInputLayout.isInvisible = true
            phoneNumberInputLayout.isVisible = true
        }
        validateLocalPhoneNumber(phoneNumber)
    }

    private fun onEmailTabSelected(email: String) {
        hideKeyboard(requireActivity())
        changeButtonConstraint(R.id.emailInputLayout)
        with(binding) {
            phoneNumberInputLayout.isInvisible = true
            emailInputLayout.isVisible = true
        }
        validateLocalEmail(email)
    }

    private fun validateLocalPhoneNumber(phoneNumber: String) {
        viewModel.validateLocalPhoneNumber(phoneNumber)
    }

    private fun validateNetworkPhoneNumber(phoneNumber: String) {
        viewModel.validateNetworkPhoneNumber(phoneNumber)
    }

    private fun validateLocalEmail(email: String) {
        viewModel.validateLocalEmail(email)
    }

    private fun validateNetworkEmail(email: String) {
        viewModel.validateNetworkEmail(email)
    }

    private fun savePhoneNumber(phoneNumber: String?) {
        viewModel.savePhoneNumber(phoneNumber)
    }

    private fun saveEmail(email: String?) {
        viewModel.saveEmail(email)
    }

    private fun saveInput() {
        with(binding) {
            when (tabLayout.selectedTabPosition) {
                Tab.Phone.item -> {
                    savePhoneNumber(phoneNumberEditText.text.toString().trim())
                    saveEmail(null)
                }
                Tab.Email.item -> {
                    saveEmail(emailEditText.text.toString().trim())
                    savePhoneNumber(null)
                }
            }
        }
    }

    private fun uiState(
        message: String? = null,
        isInputEnabled: Boolean = true,
        isErrorEnabled: Boolean = false,
        isButtonEnabled: Boolean,
        isButtonLoading: Boolean? = null
    ) {
        with(binding) {
            //Button
            nextButton.isEnabled = isButtonEnabled
            isButtonLoading?.let { nextButton.isLoading(it) }

            //EditText
            phoneNumberEditText.isEnabled = isInputEnabled
            emailEditText.isEnabled = isInputEnabled

            //Errors
            phoneNumberInputLayout.isErrorEnabled = isErrorEnabled
            emailInputLayout.isErrorEnabled = isErrorEnabled
            message?.let {
                when (tabLayout.selectedTabPosition) {
                    Tab.Phone.item -> phoneNumberInputLayout.error = it
                    Tab.Email.item -> emailInputLayout.error = it
                }
            }

            //TabLayout
            tabLayout.getTabAt(Tab.Phone.item)?.view?.isClickable = isInputEnabled
            tabLayout.getTabAt(Tab.Email.item)?.view?.isClickable = isInputEnabled
        }
    }

    /** Change button`s constraint */
    private fun changeButtonConstraint(inputLayout: Int) {
        with(binding) {
            val constraintSet = ConstraintSet()
            constraintSet.clone(parentLayout)
            constraintSet.connect(
                R.id.nextButton,
                ConstraintSet.TOP,
                inputLayout,
                ConstraintSet.BOTTOM
            )
            constraintSet.applyTo(parentLayout)
        }
    }

    private fun handleUiState(state: PhoneEmailUiState) = when (state) {
        is PhoneEmailUiState.Empty -> {
            uiState(
                isErrorEnabled = false,
                isButtonEnabled = false
            )
        }
        is PhoneEmailUiState.LocalSuccess -> {
            uiState(
                isButtonEnabled = true
            )
        }
        is PhoneEmailUiState.NetworkSuccess -> {
            saveInput()
            goToPasswordScreen()
        }
        is PhoneEmailUiState.Loading -> {
            uiState(
                isInputEnabled = false,
                isButtonEnabled = true
            )
        }
        is PhoneEmailUiState.Error -> {
            uiState(
                message = state.uiText.asString(requireContext()),
                isButtonEnabled = false,
                isButtonLoading = false
            )
        }
        is PhoneEmailUiState.NoInternetError -> {
            uiState(
                isButtonEnabled = false,
                isButtonLoading = false
            )
            requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.no_internet_connection))
        }
        is PhoneEmailUiState.ServerError -> {
            uiState(
                isButtonEnabled = true,
                isButtonLoading = false
            )
            requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.server_error))
        }
        is PhoneEmailUiState.UnknownError -> {
            Log.d(TAG, state.message)
            uiState(
                isButtonEnabled = true,
                isButtonLoading = false
            )
        }
    }

    private fun goToPasswordScreen() =
        findNavController().safeNavigate(PhoneEmailFragmentDirections.actionPhoneEmailFragmentToPasswordFragment())

    private fun goToLoginScreen() {
        viewModel.resetAllCache()
        pop()
    }

    companion object {
        private val TAG = PhoneEmailFragment::class.simpleName
    }
}
