package com.dolearnn.cg.usecase

import kotlin.random.Random

object RandomNumberUseCase {
    fun getRandom(min: Float, max: Float): Float = Random.nextFloat() * (max - min + 0.1f) + min
}
