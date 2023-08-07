package com.glechyk.gram.delta.core.utils.viewbinding

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.glechyk.gram.delta.core.utils.handler.MainHandler
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class ViewBindingPropertyDelegate<in R : Any, T : ViewBinding>(
    private val viewBinder: (R) -> T
) : ReadOnlyProperty<R, T> {

    private var viewBinding: T? = null
    private val lifecycleObserver = ClearOnDestroyViewLifecycleObserver()
    private var thisRef: R? = null

    protected abstract fun getLifecycleOwner(thisRef: R): LifecycleOwner

    @MainThread
    override fun getValue(thisRef: R, property: KProperty<*>): T {
        viewBinding?.let { return it }

        this.thisRef = thisRef

        val lifecycle = getLifecycleOwner(thisRef).lifecycle
        val viewBinding = viewBinder(thisRef)

        Log.d("ViewBinding", "State before if${lifecycle.currentState}")
        Log.d("ViewBinding", "ViewBinding before if${this.viewBinding}")
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            Log.d("ViewBinding", "State in if branch${lifecycle.currentState}")
            MainHandler.runOnUiThread({ this.viewBinding = null })
        } else {
            Log.d("ViewBinding", "State in else branch${lifecycle.currentState}")
            lifecycle.addObserver(lifecycleObserver)
            this.viewBinding = viewBinding
        }
        Log.d("ViewBinding", "ViewBinding before if${this.viewBinding}")
        return viewBinding
    }

    @MainThread
    fun clear() {
        val thisRef = thisRef ?: return
        this.thisRef = null

        getLifecycleOwner(thisRef).lifecycle.removeObserver(lifecycleObserver)

        MainHandler.runOnUiThread({ this.viewBinding = null })
    }

    @Suppress("unused", "UNUSED_PARAMETER")
    private inner class ClearOnDestroyViewLifecycleObserver : DefaultLifecycleObserver {

        @MainThread
        override fun onDestroy(owner: LifecycleOwner) {
            clear()
        }
    }
}
