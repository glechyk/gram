package com.glechyk.gram.delta.core.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope

abstract class BaseFragment<VIEW_BINDING : ViewBinding, VIEW_MODEL : ViewModel>(
    @LayoutRes layout: Int
) : Fragment(layout), AndroidScopeComponent {

    override val scope by fragmentScope()

    protected abstract val binding: VIEW_BINDING

    protected abstract val viewModel: VIEW_MODEL

    protected val parentOfNavHostFragment by lazy { requireParentFragment().requireParentFragment() as BaseFragment<*, *> }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragmentScope()
        setUpView()
        observeViewModel()
    }

    protected open fun setUpFragmentScope() = Unit

    protected open fun setUpView() = Unit

    protected open fun observeViewModel() = Unit

    override fun onDestroyView() {
        clear()
        super.onDestroyView()
    }

    // clear all dependencies or view links in case of interrupted animation with navigation
    protected open fun clear() = Unit

    protected fun <T> Flow<T>.observe(action: suspend (T) -> Unit) {
        onEach { action.invoke(it) }
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .launchIn(lifecycleScope)
    }

    protected fun NavDirections.go() = findNavController().navigate(this)

    protected fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
    }

    protected fun pop() = findNavController().popBackStack()
}
