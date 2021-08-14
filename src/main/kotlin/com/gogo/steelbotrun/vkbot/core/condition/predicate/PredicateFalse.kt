package com.gogo.steelbotrun.vkbot.core.condition.predicate

import com.gogo.steelbotrun.vkbot.core.condition.Condition

class PredicateFalse: Condition() {
	override fun resolve(vararg args: Any) = false
}
