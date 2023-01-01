package com.dolearnn.cg.utils

import kotlin.random.Random

object RandomNumberUtil {
    fun getRandom(min: Float, max: Float): Float = Random.nextFloat() * (max - min + 0.1f) + min
}
