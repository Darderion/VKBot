package com.gogo.steelbotrun.vkbot

import kotlin.random.Random

class Random {
	companion object {
		fun roll(percent: Int) = percent > Random.nextInt(0, 100)
	}
}
