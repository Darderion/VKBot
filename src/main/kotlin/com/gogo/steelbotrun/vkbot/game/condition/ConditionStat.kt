package com.gogo.steelbotrun.vkbot.game.condition

import com.gogo.steelbotrun.vkbot.core.condition.ComparisonType
import com.gogo.steelbotrun.vkbot.core.condition.ConditionComparison
import com.gogo.steelbotrun.vkbot.game.character.stats.Stats

class ConditionStat(val stat: String, comparisonType: ComparisonType, value: Double): ConditionComparison(comparisonType, value) {
	override fun resolve(args: Map<Class<Any>, Any>): Boolean {
		comparedValue = (args[Stats().javaClass] as Stats)[stat]

		return super.resolve(args)
	}

	override fun toString() = "$stat $comparisonType $value"
}
