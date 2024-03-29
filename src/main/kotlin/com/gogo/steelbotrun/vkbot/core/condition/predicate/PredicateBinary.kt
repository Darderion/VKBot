package com.gogo.steelbotrun.vkbot.core.condition.predicate

import com.gogo.steelbotrun.vkbot.core.condition.Condition

fun Boolean.toPredicate() = if (this) PredicateTrue() else PredicateFalse()

abstract class PredicateBinary(vararg conditions: Condition) : Condition() {
	protected val condition1: Condition
	protected val condition2: Condition

	init {
		val count = conditions.count()
		val (predicate1, predicate2) = when (count) {
			0    -> Pair(PredicateTrue(), PredicateTrue())
			1    -> Pair(conditions.first(), conditions.first())
			2    -> Pair(conditions[0], conditions[1])
			else -> {
				getPredicateTree(*conditions)
			}
		}
		condition1 = predicate1
		condition2 = predicate2
	}

	override fun resolve(args: Map<Class<Any>, Any>) = condition1.interpret(args) && condition2.interpret(args)

	/**
	 * Returns a pair of Conditions where each Condition represents half of arguments' conditions
	 */
	abstract fun getPredicateTree(vararg conditions: Condition): Pair<Condition, Condition>
}
