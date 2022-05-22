package com.dolearnn.cg.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dolearnn.cg.DoLearnnCgApplication
import com.dolearnn.cg.R
import com.dolearnn.cg.data.database.algorithm.Algorithm

class AlgorithmsViewModel(application: Application) : AndroidViewModel(application) {
    private val algorithmDao by lazy {
        (application as DoLearnnCgApplication).database.algorithmDao()
    }

    val algorithms: LiveData<List<Algorithm>>
        get() = algorithmDao.getAlgorithms()

    private val cardItemBgs: IntArray = try {
        val typedArray = application.resources.obtainTypedArray(R.array.card_items_bg)
        IntArray(typedArray.length()) {
            typedArray.getResourceId(it, 0)
        }.also {
            typedArray.recycle()
        }
    } catch (e: Exception) {
        intArrayOf(
            R.drawable.ic_bg_gradient_card_one,
            R.drawable.ic_bg_gradient_card_two,
            R.drawable.ic_bg_gradient_card_three,
            R.drawable.ic_bg_gradient_card_four,
        )
    }

    fun getItemBg(index: Int): Int = cardItemBgs[index % cardItemBgs.size]
}