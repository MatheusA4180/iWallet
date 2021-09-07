package com.example.iwallet.features.resume.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentResumeBinding
import com.example.iwallet.features.resume.ProfileDialog
import com.example.iwallet.features.resume.adapter.ListProductCompactAdapter
import com.example.iwallet.features.resume.adapter.ViewPagerNewsAdapter
import com.example.iwallet.features.resume.viewmodel.ResumeViewModel
import com.example.iwallet.utils.helperfunctions.HelperFunctions.converterToReal
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResumeFragment : Fragment() {

    private var _binding: FragmentResumeBinding? = null
    private val binding: FragmentResumeBinding get() = _binding!!
    private val viewModel: ResumeViewModel by viewModel()
    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.to_bottom_anim
        )
    }
    private var clicked = false
    private var pieChart: PieChart? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResumeBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.requestListProducts()
        viewModel.requestCurrentBalanceAndProfitability()
        viewModel.requestDataForGraph()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.pagerNews.adapter = ViewPagerNewsAdapter(this)

        pieChart = binding.pieChartView

        viewModel.listProducts.observe(viewLifecycleOwner, {
            binding.listProductsDescription.adapter = ListProductCompactAdapter(it)
        })

        viewModel.dataAndColorsGraph.observe(viewLifecycleOwner, {
            pieChart!!.isVisible = true
            initPieChart()
            showPieChart(it.pieEntries, it.colors)
        })

        binding.addButton.setOnClickListener {
            setVisibility(clicked)
            setAnimation(clicked)
            clicked = !clicked
        }

        binding.toolbarResume.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profile -> {
                    ProfileDialog().show(childFragmentManager, null)
                    true
                }
                else -> false
            }
        }

        binding.addTransaction.setOnClickListener {
            navigateToProductActivity(LIST_PRODUCT)
        }

        binding.addProduct.setOnClickListener {
            navigateToProductActivity(NEW_PRODUCT)
        }

        viewModel.currentBalance.observe(viewLifecycleOwner, {
            binding.currentBalance.text = converterToReal(it)
        })

        viewModel.profitability.observe(viewLifecycleOwner, {
            binding.profitability.text = "0,00%"
            //"${it.toString().replace(".",",")} %"
        })

    }

    private fun navigateToProductActivity(argument: String) {
        findNavController().navigate(
            ResumeFragmentDirections
                .actionResumeFragmentToProductsActivity(argument)
        )
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            binding.addTransaction.isVisible = true
            binding.addProduct.isVisible = true
        } else {
            binding.addTransaction.isVisible = false
            binding.addProduct.isVisible = false
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.addTransaction.startAnimation(fromBottom)
            binding.addProduct.startAnimation(fromBottom)
            binding.addButton.startAnimation(rotateOpen)
        } else {
            binding.addTransaction.startAnimation(toBottom)
            binding.addProduct.startAnimation(toBottom)
            binding.addButton.startAnimation(rotateClose)
        }
    }

    private fun initPieChart() {
        pieChart!!.setUsePercentValues(false)
        pieChart!!.description.isEnabled = false
        pieChart!!.legend.isEnabled = false
        pieChart!!.isHighlightPerTapEnabled = false
        pieChart!!.animateY(1400, Easing.EaseInOutQuad)
        pieChart!!.isDrawHoleEnabled = false
    }

    private fun showPieChart(pieEntries: ArrayList<PieEntry>, colors: ArrayList<Int>) {
        val pieDataSet = PieDataSet(pieEntries, null)
        pieDataSet.valueTextSize = 12f
        pieDataSet.colors = colors

        val pieData = PieData(pieDataSet)
        pieData.setDrawValues(false)
        pieChart!!.data = pieData
        pieChart!!.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val LIST_PRODUCT = "ListProduct"
        const val NEW_PRODUCT = "NewProduct"
    }
}