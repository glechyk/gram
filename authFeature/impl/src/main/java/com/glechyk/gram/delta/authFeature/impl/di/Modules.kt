package com.glechyk.gram.delta.authFeature.impl.di

import com.glechyk.gram.delta.authFeature.impl.data.repository.ValidationRepositoryImpl
import com.glechyk.gram.delta.authFeature.impl.data.network.api.AuthApi
import com.glechyk.gram.delta.authFeature.impl.data.repository.AuthRepositoryImpl
import com.glechyk.gram.delta.authFeature.impl.domain.repository.AuthRepository
import com.glechyk.gram.delta.authFeature.impl.domain.repository.ValidationRepository
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CacheBirthdayUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CacheEmailUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CacheFullNameUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CacheNicknameUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CachePasswordUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CachePhoneNumberUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.GetCurrentTimerStateUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.GetUserCachedLoginUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsEmailValidLocalUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsEmailValidNetworkUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsFullNameValidUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.LoginUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsNicknameValidUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsPasswordConfirmationValidUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsPasswordValidUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsPhoneNumberValidLocalUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsPhoneNumberValidNetworkUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.IsVerificationCodeConfirmedUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.LoginGoogleUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.LoginFacebookUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedBirthdayUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedEmailUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedFullNameUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedNicknameUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedPasswordUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedPhoneNumberUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObservePasswordConfirmationUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObservePasswordValidationUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveTimerStateUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ResetAllCacheUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.SendVerificationCodeUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.SignUpUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.StartVerificationCodeTimerUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.CacheBirthdayUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.CacheEmailUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.CacheFullNameUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.CacheNicknameUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.CachePasswordUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.CachePhoneNumberUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.GetCurrentTimerStateUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.GetUserCachedLoginUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.IsEmailValidLocalUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.IsEmailValidNetworkUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.IsFullNameValidUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.LoginUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.IsNicknameValidUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.IsPasswordConfirmationValidUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.IsPasswordValidUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.IsPhoneNumberValidLocalUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.IsPhoneNumberValidNetworkUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.IsVerificationCodeConfirmedUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.LoginGoogleUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.LoginFacebookUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.ObserveCachedBirthdayUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.ObserveCachedEmailUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.ObserveCachedFullNameUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.ObserveCachedNicknameUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.ObserveCachedPasswordUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.ObserveCachedPhoneNumberUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.ObservePasswordConfirmationUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.ObservePasswordValidationUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.ObserveTimerStateUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.ResetAllCacheUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.SendVerificationCodeUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.SignUpUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.impl.StartVerificationCodeTimerTimerUseCaseImpl
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.auth.AuthFragment
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.auth.AuthViewModel
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.login.LoginFragment
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.login.LoginViewModel
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.birthday.BirthdayFragment
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.birthday.BirthdayViewModel
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.fullName.FullNameFragment
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.fullName.FullNameViewModel
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.nickname.NicknameFragment
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.nickname.NicknameViewModel
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.password.PasswordFragment
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.password.PasswordViewModel
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.phoneEmail.PhoneEmailFragment
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.phoneEmail.PhoneEmailViewModel
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.verifyCode.VerificationCodeFragment
import com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.verifyCode.VerificationCodeViewModel
import com.glechyk.gram.delta.core.utils.Constants
import com.glechyk.gram.delta.network.api.Network
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val authFeatureDataModule = module {
    scope<AuthFragment> {
        scoped {
            get<Network>().getService(
                AuthApi::class.java,
                get(named(Constants.AUTH_BASE_URL))
            )
        }
        scoped<AuthRepository> { AuthRepositoryImpl(authApi = get(), tokenManager = get()) }
        scoped<ValidationRepository> { ValidationRepositoryImpl(authApi = get()) }
    }
}

private val authFeatureDomainModule = module {

    scope<AuthFragment> {
        scoped<GetUserCachedLoginUseCase> { GetUserCachedLoginUseCaseImpl(repository = get()) }
        scoped<ResetAllCacheUseCase> { ResetAllCacheUseCaseImpl(repository = get()) }
    }

    scope<LoginFragment> {
        scoped<LoginUseCase> { LoginUseCaseImpl(repository = get()) }
        scoped<LoginGoogleUseCase> { LoginGoogleUseCaseImpl(repository = get()) }
        scoped<LoginFacebookUseCase> { LoginFacebookUseCaseImpl(repository = get()) }
    }

    scope<PhoneEmailFragment> {
        scoped<IsPhoneNumberValidLocalUseCase> { IsPhoneNumberValidLocalUseCaseImpl(repository = get()) }
        scoped<IsPhoneNumberValidNetworkUseCase> { IsPhoneNumberValidNetworkUseCaseImpl(repository = get()) }
        scoped<IsEmailValidLocalUseCase> { IsEmailValidLocalUseCaseImpl(repository = get()) }
        scoped<IsEmailValidNetworkUseCase> { IsEmailValidNetworkUseCaseImpl(repository = get()) }
        scoped<CachePhoneNumberUseCase> { CachePhoneNumberUseCaseImpl(repository = get()) }
        scoped<CacheEmailUseCase> { CacheEmailUseCaseImpl(repository = get()) }
        scoped<ObserveCachedPhoneNumberUseCase> { ObserveCachedPhoneNumberUseCaseImpl(repository = get()) }
        scoped<ObserveCachedEmailUseCase> { ObserveCachedEmailUseCaseImpl(repository = get()) }
    }

    scope<PasswordFragment> {
        scoped<IsPasswordValidUseCase> { IsPasswordValidUseCaseImpl(repository = get()) }
        scoped<IsPasswordConfirmationValidUseCase> {
            IsPasswordConfirmationValidUseCaseImpl(
                repository = get()
            )
        }
        scoped<CachePasswordUseCase> { CachePasswordUseCaseImpl(repository = get()) }
        scoped<ObservePasswordValidationUseCase> { ObservePasswordValidationUseCaseImpl(repository = get()) }
        scoped<ObservePasswordConfirmationUseCase> {
            ObservePasswordConfirmationUseCaseImpl(
                repository = get()
            )
        }
        scoped<ObserveCachedPasswordUseCase> { ObserveCachedPasswordUseCaseImpl(repository = get()) }
    }

    scope<NicknameFragment> {
        scoped<IsNicknameValidUseCase> { IsNicknameValidUseCaseImpl(repository = get()) }
        scoped<CacheNicknameUseCase> { CacheNicknameUseCaseImpl(repository = get()) }
        scoped<ObserveCachedNicknameUseCase> { ObserveCachedNicknameUseCaseImpl(repository = get()) }
    }

    scope<FullNameFragment> {
        scoped<IsFullNameValidUseCase> { IsFullNameValidUseCaseImpl(repository = get()) }
        scoped<CacheFullNameUseCase> { CacheFullNameUseCaseImpl(repository = get()) }
        scoped<ObserveCachedFullNameUseCase> { ObserveCachedFullNameUseCaseImpl(repository = get()) }
    }

    scope<BirthdayFragment> {
        scoped<CacheBirthdayUseCase> { CacheBirthdayUseCaseImpl(repository = get()) }
        scoped<ObserveCachedBirthdayUseCase> { ObserveCachedBirthdayUseCaseImpl(repository = get()) }
    }

    scope<VerificationCodeFragment> {
        scoped<SendVerificationCodeUseCase> { SendVerificationCodeUseCaseImpl(repository = get()) }
        scoped<IsVerificationCodeConfirmedUseCase> {
            IsVerificationCodeConfirmedUseCaseImpl(
                repository = get()
            )
        }
        scoped<StartVerificationCodeTimerUseCase> {
            StartVerificationCodeTimerTimerUseCaseImpl(
                repository = get()
            )
        }
        scoped<GetCurrentTimerStateUseCase> { GetCurrentTimerStateUseCaseImpl(repository = get()) }
        scoped<ObserveTimerStateUseCase> { ObserveTimerStateUseCaseImpl(repository = get()) }
        scoped<SignUpUseCase> { SignUpUseCaseImpl(repository = get()) }
    }
}

private val authFeaturePresentationModule = module {
    scope<AuthFragment> {
        viewModel {
            AuthViewModel()
        }
    }

    scope<LoginFragment> {
        viewModel {
            LoginViewModel(
                loginUseCase = get(),
                loginFacebookUseCase = get(),
                loginGoogleUseCase = get()
            )
        }
    }

    scope<PhoneEmailFragment> {
        viewModel {
            PhoneEmailViewModel(
                isPhoneNumberValidLocalUseCase = get(),
                isPhoneNumberValidNetworkUseCase = get(),
                isEmailValidLocalUseCase = get(),
                isEmailValidNetworkUseCase = get(),
                cachePhoneNumberUseCase = get(),
                cacheEmailUseCase = get(),
                resetAllCacheUseCase = get(),
                getUserCachedLoginUseCase = get(),
                observeCachedPhoneNumberUseCase = get(),
                observeCachedEmailUseCase = get()
            )
        }
    }

    scope<PasswordFragment> {
        viewModel {
            PasswordViewModel(
                isPasswordValidUseCase = get(),
                isPasswordConfirmationValidUseCase = get(),
                cachePasswordUseCase = get(),
                observePasswordValidationUseCase = get(),
                observePasswordConfirmationUseCase = get(),
                observeCachedPasswordUseCase = get()
            )
        }
    }

    scope<NicknameFragment> {
        viewModel {
            NicknameViewModel(
                isNicknameValidUseCase = get(),
                cacheNicknameUseCase = get(),
                observeCachedNicknameUseCase = get()
            )
        }
    }

    scope<FullNameFragment> {
        viewModel {
            FullNameViewModel(
                isFullNameValidUseCase = get(),
                cacheFullNameUseCase = get(),
                observeCachedFullNameUseCase = get()
            )
        }
    }

    scope<BirthdayFragment> {
        viewModel {
            BirthdayViewModel(
                cacheBirthdayUseCase = get(),
                observeCachedBirthdayUseCase = get()
            )
        }
    }

    scope<VerificationCodeFragment> {
        viewModel {
            VerificationCodeViewModel(
                signUpUseCase = get(),
                getUserCachedLoginUseCase = get(),
                sendVerificationCodeUseCase = get(),
                isVerificationCodeConfirmedUseCase = get(),
                startVerificationCodeTimerUseCase = get(),
                getCurrentTimerStateUseCase = get(),
                observeTimerStateUseCase = get()
            )
        }
    }
}

val authFeatureModule = module {
    includes(
        authFeatureDataModule,
        authFeatureDomainModule,
        authFeaturePresentationModule
    )
}
