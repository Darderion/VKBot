package com.gogo.steelbotrun.vkbot.core.condition

import com.gogo.steelbotrun.vkbot.core.condition.ComparisonType
import com.gogo.steelbotrun.vkbot.core.condition.ComparisonType.*
import com.gogo.steelbotrun.vkbot.core.condition.Condition
import com.gogo.steelbotrun.vkbot.core.condition.equalsDelta
import com.gogo.steelbotrun.vkbot.game.character.stats.Stats

abstract class ConditionComparison(val comparisonType: ComparisonType, val value: Double) : Condition() {
	protected var comparedValue: Double? = null

	override fun resolve(vararg args: Any) = if (comparedValue != null) when (comparisonType) {
		Equal          -> comparedValue!!.equalsDelta(value)
		Greater        -> comparedValue!! > value
		Less           -> comparedValue!! < value
		GreaterOrEqual -> comparedValue!! > value || comparedValue!!.equalsDelta(value)
		LessOrEqual    -> comparedValue!! < value || comparedValue!!.equalsDelta(value)
	} else throw Error("No value to compare with")
}
