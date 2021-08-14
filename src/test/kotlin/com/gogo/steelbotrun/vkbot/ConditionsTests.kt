package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.core.condition.ComparisonType
import com.gogo.steelbotrun.vkbot.game.character.stats.Stats
import com.gogo.steelbotrun.vkbot.game.condition.ConditionStat
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo

class ConditionsTests: StringSpec({
	"ConditionStat should compare stats" {
		val condition = ConditionStat("Strength", ComparisonType.greater, 3.0)

		condition.resolve(Stats("Strength" to 2.0)) shouldBeEqualComparingTo false
		condition.resolve(Stats("Strength" to 3.0)) shouldBeEqualComparingTo false
		condition.resolve(Stats("Strength" to 4.0)) shouldBeEqualComparingTo true
	}
})
