package com.glechyk.gram.delta.authFeature.impl.presentation.screen.signup.birthday

import android.os.Bundle
import android.text.InputType
import android.util.Log
import com.chi.interngram.delta.authFeature.impl.R
import com.chi.interngram.delta.authFeature.impl.databinding.FragmentBirthdayBinding
import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.addOnTextChangedListener
import com.glechyk.gram.delta.core.utils.clickAction
import com.glechyk.gram.delta.core.utils.commonBirthdayMaterialDatePicker
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class BirthdayFragment : BaseFragment<FragmentBirthdayBinding, BirthdayViewModel>(
    R.layout.fragment_birthday
) {
    override val binding by viewBinding({
        FragmentBirthdayBinding.bind(it)
    })

    override val viewModel by viewModel<BirthdayViewModel>()

    private lateinit var datePicker: MaterialDatePicker<Long>

    override fun setUpFragmentScope() {
        val authFragmentScope = parentOfNavHostFragment.scope
        scope.linkTo(authFragmentScope)
    }

    override fun setUpView() {
        setUpTextInput()
        setUpButtons()
        setUpDatePicker()
    }

    override fun observeViewModel() {
        with(viewModel) {
            birthdayFlow.observe {
                binding.birthdayTextInputEditText.setText(
                    uiDateFormatter.format(
                        it
                    )
                )
            }
        }
    }

    private fun setUpDatePicker() {
        datePicker = commonBirthdayMaterialDatePicker
        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            binding.birthdayTextInputEditText.setText(
                viewModel.uiDateFormatter.format(
                    selectedDate
                )
            )
            viewModel.saveBirthday(
                selectedDate
            )
        }
    }

    private fun setUpTextInput() {
        with(binding.birthdayTextInputEditText) {
            inputType = InputType.TYPE_NULL
            keyListener = null

            addOnTextChangedListener { string ->
                binding.progressButton.isEnabled = string.isNotBlank()
            }

            clickAction {
                datePicker.show(parentFragmentManager, BirthdayFragment::class.simpleName)
            }
        }
    }

    private fun setUpButtons() {
        with(binding) {
            progressButton.isEnabled = false

            progressButton.clickAction {
                BirthdayFragmentDirections.actionBirthdayFragmentToVerificationCodeFragment().go()
            }

            backButton.setOnClickListener {
                viewModel.saveBirthday(null)
                pop()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ViewBinding", "BirthdayFragment lifecycle state: onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("ViewBinding", "BirthdayFragment lifecycle state: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ViewBinding", "BirthdayFragment lifecycle state: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ViewBinding", "BirthdayFragment lifecycle state: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ViewBinding", "BirthdayFragment lifecycle state: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ViewBinding", "BirthdayFragment lifecycle state: onDestroy")
    }

}
