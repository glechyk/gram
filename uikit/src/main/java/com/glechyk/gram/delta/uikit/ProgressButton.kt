package com.glechyk.gram.delta.uikit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.chi.interngram.delta.uikit.R
import com.glechyk.gram.delta.core.utils.clickAction
import com.chi.interngram.delta.uikit.databinding.ProgressButtonBinding

class ProgressButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ProgressButtonBinding.inflate(LayoutInflater.from(context), this, true)

    private var textProgressButton: String
        set(value) {
            binding.button.text = value
        }
        get() = binding.button.text.toString()

    init {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.ProgressButton, 0, 0)
        textProgressButton = arr.getString(R.styleable.ProgressButton_textProgressButton)
            ?: "".also { arr.recycle() }

    }

    fun isLoading(status: Boolean) {
        with(binding) {
            if (status) {
                circularProgressIndicator.visibility = View.VISIBLE
                button.isClickable = false
                button.setTextColor(context.getColor(R.color.transparent))
            } else {
                circularProgressIndicator.visibility = View.GONE
                button.isClickable = true
                button.setTextColor(context.getColor(R.color.white))
            }
        }
    }

    fun clickAction(action: (View) -> Unit) {
        binding.button.clickAction {
            isLoading(true)
            action.invoke(it)
        }
    }

    override fun setEnabled(enabled: Boolean) {
        binding.button.isEnabled = enabled
    }

    override fun isEnabled() = binding.button.isEnabled

}
