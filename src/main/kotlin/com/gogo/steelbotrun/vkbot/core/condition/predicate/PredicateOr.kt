package com.gogo.steelbotrun.vkbot.core.condition.predicate

import com.gogo.steelbotrun.vkbot.core.condition.Condition

class PredicateOr(vararg conditions: Condition): PredicateBinary(*conditions) {
	override fun getPredicateTree(vararg conditions: Condition) = Pair(
		PredicateOr(*conditions.copyOfRange(0, (conditions.count() + 1) / 2)),
		PredicateOr(*conditions.copyOfRange((conditions.count() + 1) / 2, conditions.count()))
	)

	override fun resolve(args: Map<Class<Any>, Any>) = condition1.interpret(args) || condition2.interpret(args)
}
