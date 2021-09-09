package com.example.iwallet.features.resume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import com.example.iwallet.R
import com.example.iwallet.databinding.ActivityProductsBinding
import com.example.iwallet.databinding.ActivityThemeNewsBinding
import com.example.iwallet.features.resume.fragments.ResumeFragment

class ThemeNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThemeNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThemeNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}