package com.glechyk.gram.delta.splashFeature.impl.di

import com.glechyk.gram.delta.splashFeature.impl.data.SplashRepositoryImpl
import com.glechyk.gram.delta.splashFeature.impl.domain.repository.SplashRepository
import com.glechyk.gram.delta.splashFeature.impl.domain.usecase.api.CheckUserAuthenticationUseCase
import com.glechyk.gram.delta.splashFeature.impl.domain.usecase.impl.CheckUserAuthenticationUseCaseImpl
import com.glechyk.gram.delta.splashFeature.impl.presentation.screen.splash.SplashFragment
import com.glechyk.gram.delta.splashFeature.impl.presentation.screen.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val splashFeatureDataModule = module {
    scope<SplashFragment> {
        scoped<SplashRepository> { SplashRepositoryImpl() }
    }
}

private val splashFeatureDomainModule = module {
    scope<SplashFragment> {
        scoped<CheckUserAuthenticationUseCase> {
            CheckUserAuthenticationUseCaseImpl(
                repository = get()
            )
        }
    }
}

private val splashFeaturePresentationModule = module {
    scope<SplashFragment> {
        viewModel {
            SplashViewModel(
                checkUserAuthenticationUseCase = get()
            )
        }
    }
}

val splashFeatureModule = module {
    includes(
        splashFeatureDataModule,
        splashFeatureDomainModule,
        splashFeaturePresentationModule
    )
}
