package com.gogo.steelbotrun.vkbot.utils.random

import kotlin.random.Random

class Random {
	companion object {
		fun roll(percent: Int) = percent > Random.nextInt(0, 100)
		fun roll(percent: Double) = percent > Random.nextInt(0, 100)
	}
}
