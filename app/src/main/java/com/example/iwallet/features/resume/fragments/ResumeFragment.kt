package com.example.iwallet.features.resume.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentResumeBinding
import com.example.iwallet.features.resume.ProfileDialog
import com.example.iwallet.features.resume.adapter.ViewPagerNewsAdapter
import com.example.iwallet.features.resume.viewmodel.ResumeViewModel
import com.example.iwallet.utils.helperfunctions.HelperFunctions.converterToReal
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResumeFragment: Fragment() {

    private var _binding: FragmentResumeBinding? = null
    private val binding: FragmentResumeBinding get() = _binding!!
    private val viewModel: ResumeViewModel by viewModel()
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.to_bottom_anim) }
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

        viewModel.requestCurrentBalanceAndProfitability()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.pagerNews.adapter = ViewPagerNewsAdapter(this)

        pieChart = binding.pieChartView
        initPieChart()
        showPieChart()

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
            navigateToProductActivity(ADD_OR_SUBTRACT_PRODUCT)
        }

        binding.addProduct.setOnClickListener {
            navigateToProductActivity(NEW_PRODUCT)
        }

        viewModel.currentBalance.observe(viewLifecycleOwner,{
            binding.currentBalance.text = converterToReal(it)
        })

        viewModel.profitability.observe(viewLifecycleOwner,{
            binding.profitability.text = "${it.toString().replace(".",",")} %"
        })

        viewModel.showLoading.observe(viewLifecycleOwner,{
            binding.progressCircular.isVisible = it
        })

    }

    private fun navigateToProductActivity(argument: String){
        findNavController().navigate(ResumeFragmentDirections
            .actionResumeFragmentToProductsActivity(argument)
        )
    }

    private fun setVisibility(clicked: Boolean){
        if(!clicked){
            binding.addTransaction.isVisible = true
            binding.addProduct.isVisible = true
        }else{
            binding.addTransaction.isVisible = false
            binding.addProduct.isVisible = false
        }
    }

    private fun setAnimation(clicked: Boolean){
        if(!clicked){
            binding.addTransaction.startAnimation(fromBottom)
            binding.addProduct.startAnimation(fromBottom)
            binding.addButton.startAnimation(rotateOpen)
        }else{
            binding.addTransaction.startAnimation(toBottom)
            binding.addProduct.startAnimation(toBottom)
            binding.addButton.startAnimation(rotateClose)
        }
    }

    private fun initPieChart() {
        //using percentage as values instead of amount
        pieChart!!.setUsePercentValues(true)

        //remove the description label on the lower left corner, default true if not set
        //pieChart!!.description.isEnabled = false
        pieChart!!.legend.isEnabled = false
        //enabling the user to rotate the chart, default true
        pieChart!!.isRotationEnabled = true
        //adding friction when rotating the pie chart
        pieChart!!.dragDecelerationFrictionCoef = 0.9f
        //setting the first entry start from right hand side, default starting from top
        pieChart!!.rotationAngle = 0f

        //highlight the entry when it is tapped, default true if not set
        pieChart!!.isHighlightPerTapEnabled = true
        //adding animation so the entries pop up from 0 degree
        pieChart!!.animateY(1400, Easing.EaseInOutQuad)
        //setting the color of the hole in the middle, default white
        pieChart!!.setHoleColor(Color.parseColor("#FFFFFF"))
    }

    private fun showPieChart() {
        val pieEntries: ArrayList<PieEntry> = ArrayList()
        val label = ""

        //initializing data
        val typeAmountMap: MutableMap<String, Int> = HashMap()
        typeAmountMap["Ações"] = 500
        typeAmountMap["Fii"] = 1000
        typeAmountMap["Tesouro Direto"] = 650

        //initializing colors for the entries
        val colors: ArrayList<Int> = ArrayList()
        colors.add(ContextCompat
            .getColor(requireContext(), R.color.red_Transaction))
        colors.add(ContextCompat
            .getColor(requireContext(), R.color.orange_Transaction))
        colors.add(ContextCompat
            .getColor(requireContext(), R.color.green_Transaction))

        //input data and fit data into pie chart entry
        for (type in typeAmountMap.keys) {
            pieEntries.add(PieEntry(typeAmountMap[type]!!.toFloat()))
        }

        //collecting the entries with label name
        val pieDataSet = PieDataSet(pieEntries, label)
        //setting text size of the value
        pieDataSet.valueTextSize = 12f
        //providing color list for coloring different entries
        pieDataSet.colors = colors
        //
        pieDataSet.valueTextColor = ContextCompat
            .getColor(requireContext(), R.color.main_theme)
        //grouping the data set from entry to chart
        val pieData = PieData(pieDataSet)
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(false)
        pieData.setValueFormatter(PercentFormatter(pieChart))
        pieChart!!.data = pieData
        pieChart!!.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val ADD_OR_SUBTRACT_PRODUCT = "AddOrSubtractProduct"
        const val NEW_PRODUCT = "NewProduct"
    }
}