package com.gogo.steelbotrun.vkbot.core.condition

import kotlin.math.abs

enum class ComparisonType {
	Equal, Greater, Less, GreaterOrEqual, LessOrEqual
}

fun String.toComparisonType() = when (this) {
	">"  -> ComparisonType.Greater
	"<"  -> ComparisonType.Less
	">=" -> ComparisonType.GreaterOrEqual
	"<=" -> ComparisonType.LessOrEqual
	"="  -> ComparisonType.Equal
	else -> throw Error("String \"$this\" is not a comparison symbol")
}

// IBM double.equals(otherDouble: Double): Boolean
fun Double.equalsDelta(other: Double) = abs(this / other - 1) < 0.000001
