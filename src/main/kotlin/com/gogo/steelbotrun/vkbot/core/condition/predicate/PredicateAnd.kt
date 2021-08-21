package com.gogo.steelbotrun.vkbot.core.condition.predicate

import com.gogo.steelbotrun.vkbot.core.condition.Condition

infix fun Condition.and(other: Condition) = PredicateAnd(this, other)

class PredicateAnd(vararg conditions: Condition): PredicateBinary(*conditions) {
	override fun getPredicateTree(vararg conditions: Condition) = Pair(
		PredicateAnd(*conditions.copyOfRange(0, (conditions.count() + 1) / 2)),
		PredicateAnd(*conditions.copyOfRange((conditions.count() + 1) / 2, conditions.count()))
	)

	override fun resolve(args: Map<Class<Any>, Any>) = condition1.interpret(args) && condition2.interpret(args)

	override fun toString() = "$condition1 and $condition2"
}
