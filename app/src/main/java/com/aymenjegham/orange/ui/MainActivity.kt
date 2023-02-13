package com.aymenjegham.orange.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.aymenjegham.orange.R
import com.aymenjegham.orange.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        bind(binding)
    }

    private fun bind(binding: ActivityMainBinding) {
        binding.lifecycleOwner = this
        setSupportActionBar(toolbar)
    }

}