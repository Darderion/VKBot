package com.gogo.steelbotrun.vkbot.core.condition

import kotlin.math.abs

enum class ComparisonType {
	Equal, Greater, Less, GreaterOrEqual, LessOrEqual
}

// IBM double.equals(otherDouble: Double): Boolean
fun Double.equalsDelta(other: Double) = abs(this/other - 1) < 0.000001
