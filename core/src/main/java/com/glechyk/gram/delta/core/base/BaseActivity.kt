package com.glechyk.gram.delta.core.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityRetainedScope

abstract class BaseActivity : AppCompatActivity(), AndroidScopeComponent {

    override val scope by activityRetainedScope()

    protected fun <T> Flow<T>.observe(action: suspend (T) -> Unit) {
        onEach { action.invoke(it) }
            .launchIn(lifecycleScope)
    }
}
