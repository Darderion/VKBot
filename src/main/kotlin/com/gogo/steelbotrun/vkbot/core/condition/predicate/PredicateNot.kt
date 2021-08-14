package com.gogo.steelbotrun.vkbot.core.condition.predicate

import com.gogo.steelbotrun.vkbot.core.condition.Condition

class PredicateNot(private val condition: Condition): Condition() {
	override fun resolve(vararg args: Any) = !condition.resolve(args)
}
