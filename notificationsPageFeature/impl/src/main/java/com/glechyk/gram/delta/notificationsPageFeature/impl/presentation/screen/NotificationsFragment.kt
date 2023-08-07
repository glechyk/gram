package com.glechyk.gram.delta.notificationsPageFeature.impl.presentation.screen

import com.glechyk.gram.delta.core.base.BaseFragment
import com.glechyk.gram.delta.core.utils.viewbinding.viewBinding
import com.chi.interngram.delta.notificationsPageFeature.impl.R
import com.chi.interngram.delta.notificationsPageFeature.impl.databinding.FragmentNotificationsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding, NotificationsViewModel>(
    R.layout.fragment_notifications
) {

    override val binding by viewBinding({
        FragmentNotificationsBinding.bind(it)
    })

    override val viewModel by viewModel<NotificationsViewModel>()

}
