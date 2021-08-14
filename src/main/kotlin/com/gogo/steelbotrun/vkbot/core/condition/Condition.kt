package com.gogo.steelbotrun.vkbot.core.condition

abstract class Condition {
	abstract fun resolve(vararg args: Any): Boolean
}
