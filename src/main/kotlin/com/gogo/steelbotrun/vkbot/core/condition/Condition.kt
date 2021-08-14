package com.gogo.steelbotrun.vkbot.core.condition

abstract class Condition {

	/**
	 * Calculates predicate's value based on arguments
	 * @param args Arguments
	 * @return True if condition is satisfied and False otherwise
	 */
	abstract fun resolve(vararg args: Any): Boolean
}
