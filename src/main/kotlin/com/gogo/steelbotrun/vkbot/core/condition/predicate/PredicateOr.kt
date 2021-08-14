package com.gogo.steelbotrun.vkbot.core.condition.predicate

import com.gogo.steelbotrun.vkbot.core.condition.Condition

class PredicateOr(vararg conditions: Condition): PredicateBinary(*conditions) {
	override fun getPredicateTree(count: Int, vararg conditions: Condition) = Pair(
		PredicateOr(*conditions.copyOfRange(0, (count + 1) / 2)),
		PredicateOr(*conditions.copyOfRange((count + 1) / 2, count))
	)

	override fun resolve(vararg args: Any) = condition1.resolve(args) || condition2.resolve(args)
}
