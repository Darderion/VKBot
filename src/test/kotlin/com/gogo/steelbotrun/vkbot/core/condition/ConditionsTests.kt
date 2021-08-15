package com.gogo.steelbotrun.vkbot.core.condition

import com.gogo.steelbotrun.vkbot.game.character.stats.Stats
import com.gogo.steelbotrun.vkbot.game.condition.ConditionStat
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo

class ConditionsTests: StringSpec({
	"ConditionStat should compare stats" {
		val condition = ConditionStat("Strength", ComparisonType.Greater, 3.0)

		condition.interpret(Stats("Strength" to 2.0)) shouldBeEqualComparingTo false
		condition.interpret(Stats("Strength" to 3.0)) shouldBeEqualComparingTo false
		condition.interpret(Stats("Strength" to 4.0)) shouldBeEqualComparingTo true
	}
	"Condition should only accept arguments with distinct argument types" {
		val condition = ConditionStat("Strength", ComparisonType.Greater, 3.0)

		condition.interpret(Stats("Strength" to 2.0), 1)

		shouldThrowAny {
			condition.interpret(Stats("Strength" to 3.0), 1, 2) shouldBeEqualComparingTo false
		}
	}
})
