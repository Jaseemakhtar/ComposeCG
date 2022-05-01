package com.dolearnn.cg.os.utils

import kotlin.random.Random

object RandomNumber {
    fun getRandom(min: Float, max: Float): Float = Random.nextFloat() * (max - min + 0.1f) + min
}
