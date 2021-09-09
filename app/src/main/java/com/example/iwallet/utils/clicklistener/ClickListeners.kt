package com.example.iwallet.utils.clicklistener

object ClickListeners {

    interface ClickedProductListener {
        fun clickProductListener(
            nameBroker: String,
            nameProduct: String,
            category: String,
            color: String
        )
    }

    interface ClickedCategoryListener {
        fun clickCategoryListener(positionRecyclerView: Int, category: String)
    }

    interface ClickedProfileListener {
        fun clickProfileListener(positionRecyclerView: Int)
    }

}
