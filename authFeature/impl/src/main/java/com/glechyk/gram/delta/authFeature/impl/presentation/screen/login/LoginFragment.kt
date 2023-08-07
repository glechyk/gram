package com.glechyk.gram.delta.authFeature.impl.presentation.screen.login

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.chi.interngram.delta.authFeature.impl.R
import com.chi.interngram.delta.authFeature.impl.databinding.FragmentLoginBinding
import com.glechyk.gram.delta.authFeature.impl.presentation.model.LoginUiState
import com.glechyk.gram.delta.authFeature.impl.utils.LoginExceptionType
import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.Constants
import com.glechyk.gram.delta.core.utils.UiText
import com.glechyk.gram.delta.core.utils.addOnTextChangedListener
import com.glechyk.gram.delta.core.utils.asString
import com.glechyk.gram.delta.core.utils.clickAction
import com.glechyk.gram.delta.core.utils.showShortToast
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import com.glechyk.gram.delta.mainFeature.api.MainFeatureNavigator
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textview.MaterialTextView
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import java.util.Collections

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    override val binding by viewBinding({
        FragmentLoginBinding.bind(it)
    })

    override val viewModel by viewModel<LoginViewModel>()

    private val mainFeatureNavigator by inject<MainFeatureNavigator>()

    override fun setUpFragmentScope() {
        val authFragmentScope = parentOfNavHostFragment.scope
        scope.linkTo(authFragmentScope)
    }

    private val googleWebClient by inject<String>(named(Constants.WEB_CLIENT_ID))
    private lateinit var oneTapClient: SignInClient
    private lateinit var signUpRequest: BeginSignInRequest
    private lateinit var activityResultLauncher: ActivityResultLauncher<IntentSenderRequest>
    private lateinit var verifier: GoogleIdTokenVerifier

    override fun setUpView() {
        setUpGoogleSignIn()
        setUpButton()
        setUpEditText()
        setUpTextViews()
    }

    override fun observeViewModel() {
        viewModel.uiState.observe { handleUiState(it) }
    }

    private fun setUpGoogleSignIn() {

        oneTapClient = Identity
            .getSignInClient(requireActivity())

        signUpRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(googleWebClient)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

        verifier = GoogleIdTokenVerifier.Builder(
            NetHttpTransport(),
            GsonFactory.getDefaultInstance()
        )
            .setAudience(Collections.singletonList(googleWebClient))
            .build()

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    runCatching {
                        onGoogleSignInSuccess(result.data)
                    }.onFailure {
                        onGoogleSignInError(it)
                    }
                }
            }

    }

    private fun setUpButton() {
        with(binding) {
            nextButton.clickAction {
                logIn(
                    phoneEmailEditText.text.toString().trim(),
                    enterPasswordEditText.text.toString().trim()
                )
            }
        }
    }

    private fun setUpEditText() {
        with(binding) {
            phoneEmailEditText.addOnTextChangedListener {
                showErrorText(false)
            }
            enterPasswordEditText.addOnTextChangedListener {
                showErrorText(false)
            }
        }
    }

    private fun setUpTextViews() {
        with(binding) {
            forgotPasswordTextView.clickAction {
                context?.showShortToast("Forgot password")
            }
            loginGoogleTextView.clickAction {
                onClickGoogleSignInButton()
            }
            loginFacebookTextView.clickAction {
                logInFacebookInApp()
            }
            createAccountTextView.clickAction {
                goToPhoneEmailScreen()
            }
        }
    }

    private fun logIn(username: String, password: String) {
        viewModel.logIn(username, password)
    }

    private fun logInFacebookInApp() {
        val callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().logInWithReadPermissions(
            this,
            callbackManager,
            FACEBOOK_PERMISSIONS_LIST
        )
        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

                override fun onSuccess(result: LoginResult) {
                    logInFacebook(result.accessToken.token)
                }

                override fun onCancel() {
                    requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.no_internet_connection))
                }

                override fun onError(error: FacebookException) {
                    requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.server_error))
                }

            })
    }

    private fun logInFacebook(token: String) {
        viewModel.logInFacebook(token)
    }

    private fun handleUiState(uiState: LoginUiState) {
        when (uiState) {
            is LoginUiState.Success -> {
                parentOfNavHostFragment.findNavController().navigate(mainFeatureNavigator.mainFeatureDeepLink)
            }
            is LoginUiState.Error -> {
                when (uiState.uiText) {
                    LoginExceptionType.InvalidInput.uiText -> showErrorText(true)
                    LoginExceptionType.IncorrectInput.uiText -> showAlertDialog()
                    else -> {
                        with(requireContext()) {
                            showShortToast(uiState.uiText.asString(this))
                        }
                    }
                }
                binding.nextButton.isLoading(false)
            }
            is LoginUiState.NoInternetError -> {
                binding.nextButton.isLoading(false)
                requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.no_internet_connection))
            }
            is LoginUiState.ServerError -> {
                binding.nextButton.isLoading(false)
                requireContext().showShortToast(getString(com.chi.interngram.delta.core.R.string.server_error))
            }
            is LoginUiState.UnknownError -> {
                binding.nextButton.isLoading(false)
                Log.d(TAG, uiState.message)
            }
        }
    }

    private fun showErrorText(condition: Boolean) {
        with(binding) {
            if (condition) {
                errorTextView.isVisible = true
                nextButton.isEnabled = false
            } else {
                errorTextView.isVisible = false
                nextButton.isEnabled = true
            }
        }
    }

    private fun showAlertDialog() {
        val alertDialogView = layoutInflater.inflate(R.layout.login_alert_dialog, null)

        val alertDialogButton =
            alertDialogView.findViewById<MaterialTextView>(R.id.buttonDialogTextView)

        val alertDialogBuilder = MaterialAlertDialogBuilder(
            requireContext(),
            R.style.LoginAlertDialog
        ).setView(alertDialogView).create()

        alertDialogButton.clickAction { alertDialogBuilder.dismiss() }

        alertDialogBuilder.show()
    }

    private fun onClickGoogleSignInButton() {
        oneTapClient.beginSignIn(signUpRequest)
            .addOnSuccessListener(requireActivity()) { result ->
                runCatching {
                    val intentSenderRequest =
                        IntentSenderRequest.Builder(result.pendingIntent.intentSender)
                            .build()
                    activityResultLauncher.launch(intentSenderRequest)
                }.onFailure {
                    viewModel.emitUiState(
                        LoginUiState.UnknownError(
                            "Couldn't start One Tap UI. Error: ${it.message}"
                        )
                    )
                }
            }
            .addOnFailureListener(requireActivity()) {
                viewModel.emitUiState(
                    LoginUiState.UnknownError(
                        "Error: ${it.message}"
                    )
                )
            }
    }

    private fun onGoogleSignInSuccess(data: Intent?) {
        val credential = oneTapClient.getSignInCredentialFromIntent(data)
        val googleIdTokenString = credential.googleIdToken
        when {
            googleIdTokenString != null -> viewModel.logInGoogle(
                context = requireContext(),
                googleIdTokenVerifier = verifier,
                googleIdTokenString = googleIdTokenString
            )
            else -> {
                viewModel.emitUiState(LoginUiState.UnknownError("Error: Google Id Token is null"))
            }
        }
    }
    private fun onGoogleSignInError(throwable: Throwable) {
        if (throwable is ApiException) {
            when (throwable.statusCode) {
                CommonStatusCodes.NETWORK_ERROR -> {
                    viewModel.emitUiState(
                        LoginUiState.Error(UiText.StringResource(com.chi.interngram.delta.core.R.string.server_error))
                    )
                }
                else -> {
                    viewModel.emitUiState(
                        LoginUiState.UnknownError("Error: Couldn't get credential from result. ${throwable.message}")
                    )
                }
            }
        }
    }

    private fun goToPhoneEmailScreen() =
        LoginFragmentDirections.actionLoginFragmentToPhoneEmailFragment().go()

    companion object {
        private val TAG = LoginFragment::class.simpleName
        private val FACEBOOK_PERMISSIONS_LIST = listOf(
            "basic_info",
            "openid",
            "public_profile",
            "email",
            "user_birthday",
            "user_hometown",
            "user_location"
        )
    }
}
