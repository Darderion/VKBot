package com.gogo.steelbotrun.vkbot.core.condition

import com.gogo.steelbotrun.vkbot.core.condition.predicate.and
import com.gogo.steelbotrun.vkbot.core.condition.predicate.or
import com.gogo.steelbotrun.vkbot.game.character.stats.Stats
import com.gogo.steelbotrun.vkbot.game.condition.ConditionStat
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.shouldBe

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
	"(A and B) should be true if A == true and B == true" {
		val a = ConditionStat("strength", ComparisonType.Greater, 3.0)
		val b = ConditionStat("strength", ComparisonType.Less, 5.0)

		(a and b).interpret(Stats("strength" to 4.0)) shouldBe true
		(a and b).interpret(Stats("strength" to 5.0)) shouldBe false
		(a and b).interpret(Stats("strength" to 2.0)) shouldBe false
	}

	"(A or B) should be true if A == true or B == true" {
		val a = ConditionStat("strength", ComparisonType.Less, 3.0)
		val b = ConditionStat("strength", ComparisonType.Greater, 5.0)

		(a or b).interpret(Stats("strength" to 4.0)) shouldBe false
		(a or b).interpret(Stats("strength" to 5.0)) shouldBe false
		(a or b).interpret(Stats("strength" to 2.0)) shouldBe true
		(a or b).interpret(Stats("strength" to 7.0)) shouldBe true
	}
})
