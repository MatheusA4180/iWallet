package com.example.iwallet.features.resume

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import com.example.iwallet.R
import com.example.iwallet.databinding.ActivityProductsBinding
import com.example.iwallet.features.resume.fragments.ResumeFragment.Companion.ADD_OR_SUBTRACT_PRODUCT

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding
    private val arguments: ProductsActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = (supportFragmentManager
            .findFragmentById(R.id.navHostFragmentProduct) as NavHostFragment)
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.products_nav_graph)
        graph.startDestination = if (arguments.fragmentSelected == ADD_OR_SUBTRACT_PRODUCT) {
            R.id.addOrSubtractProductFragment
        } else {
            R.id.newProductFragment
        }
        navHostFragment.navController.graph = graph

    }
}
