package com.gogo.steelbotrun.vkbot.core.condition.predicate

import com.gogo.steelbotrun.vkbot.core.condition.Condition

class PredicateTrue: Condition() {
	override fun resolve(args: Map<Class<Any>, Any>) = true
}