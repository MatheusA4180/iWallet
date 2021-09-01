package com.example.iwallet.features.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iwallet.R
import com.example.iwallet.databinding.ActivityHomeBinding
import com.example.iwallet.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}