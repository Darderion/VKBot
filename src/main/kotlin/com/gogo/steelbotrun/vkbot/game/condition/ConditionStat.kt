package com.gogo.steelbotrun.vkbot.game.condition

import com.gogo.steelbotrun.vkbot.core.condition.ComparisonType
import com.gogo.steelbotrun.vkbot.core.condition.ConditionComparison
import com.gogo.steelbotrun.vkbot.game.character.stats.Stats

class ConditionStat(val stat: String, comparisonType: ComparisonType, value: Double): ConditionComparison(comparisonType, value) {
	var stats: Stats? = null
	override fun comparedValue() = stats!![stat]
}