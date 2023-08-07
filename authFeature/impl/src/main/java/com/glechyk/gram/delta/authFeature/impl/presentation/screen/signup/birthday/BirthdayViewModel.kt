package com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.birthday

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.CacheBirthdayUseCase
import com.glechyk.gram.delta.authFeature.impl.domain.usecase.api.ObserveCachedBirthdayUseCase
import kotlinx.coroutines.launch
import java.util.Locale

class BirthdayViewModel(
    private val cacheBirthdayUseCase: CacheBirthdayUseCase,
    observeCachedBirthdayUseCase: ObserveCachedBirthdayUseCase
) : ViewModel() {

    val birthdayFlow = observeCachedBirthdayUseCase.execute()

    val uiDateFormatter = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)

    fun saveBirthday(date: Long?) = viewModelScope.launch {
        cacheBirthdayUseCase.execute(date)
    }

}
