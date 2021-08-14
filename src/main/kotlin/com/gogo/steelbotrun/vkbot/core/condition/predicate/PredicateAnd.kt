package com.gogo.steelbotrun.vkbot.core.condition.predicate

import com.gogo.steelbotrun.vkbot.core.condition.Condition

class PredicateAnd(vararg conditions: Condition): PredicateBinary(*conditions) {
	override fun getPredicateTree(vararg conditions: Condition) = Pair(
		PredicateAnd(*conditions.copyOfRange(0, (conditions.count() + 1) / 2)),
		PredicateAnd(*conditions.copyOfRange((conditions.count() + 1) / 2, conditions.count()))
	)

	override fun resolve(vararg args: Any) = condition1.resolve(args) && condition2.resolve(args)
}
