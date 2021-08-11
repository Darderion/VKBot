package com.gogo.steelbotrun.vkbot.core.condition.predicate

import com.gogo.steelbotrun.vkbot.core.condition.Condition

abstract class PredicateBinary(vararg conditions: Condition): Condition() {
	protected val condition1: Condition
	protected val condition2: Condition

	init {
		val count = conditions.count()
		val (predicate1, predicate2) = when(count) {
			0 -> Pair(PredicateTrue(), PredicateTrue())
			1 -> Pair(conditions.first(), PredicateFalse())
			2 -> Pair(conditions[0], conditions[1])
			else -> {
				getPredicateTree(count, *conditions)
			}
		}
		condition1 = predicate1
		condition2 = predicate2
	}

	/**
	 * Returns a pair of Conditions where each Condition represents half of arguments' conditions
	 */
	abstract fun getPredicateTree(count: Int, vararg conditions: Condition): Pair<Condition, Condition>
}