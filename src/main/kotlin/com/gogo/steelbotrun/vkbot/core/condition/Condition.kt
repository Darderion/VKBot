package com.gogo.steelbotrun.vkbot.core.condition

abstract class Condition {

	/**
	 * Calculates predicate's value based on arguments
	 * @param args Arguments- only one argument per argument type is allowed
	 * @return True if condition is satisfied and False otherwise
	 */
	fun interpret(vararg args: Any) =
		if (args.map { it.javaClass }.toSet().count() == args.count())
			resolve(args.associateBy { it.javaClass }) else
			throw Error("Multiple arguments of the same type")

	/**
	 * Calculates predicate's value based on arguments
	 * @param args Map: Argument.Type -> Argument.Value
	 * @return True if condition is satisfied and False otherwise
	 */
	fun interpret(args: Map<Class<Any>, Any>) = resolve(args)

	/**
	 * Calculates predicate's value based on arguments with distinct argument types
	 * @param args Arguments
	 * @return True if condition is satisfied and False otherwise
	 */
	protected abstract fun resolve(args: Map<Class<Any>, Any>): Boolean
}
