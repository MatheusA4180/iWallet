package com.example.iwallet.utils.model.resume

import com.github.mikephil.charting.data.PieEntry

data class DataAndColorsGraph(
    val pieEntries: ArrayList<PieEntry>,
    val colors: ArrayList<Int>
)