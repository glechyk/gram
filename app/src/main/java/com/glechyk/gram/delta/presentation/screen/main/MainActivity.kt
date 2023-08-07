package com.glechyk.gram.delta.presentation.screen.main

import android.os.Bundle
import androidx.activity.viewModels
import com.glechyk.gram.delta.core.base.BaseActivity
import com.chi.interngram.delta.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }

}
