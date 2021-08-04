package com.gogo.steelbotrun.vkbot.battle.actions

class Move(val name: String, private val effects: List<SimpleAction>, val description: String = "") {
	fun getEffect(type: ActionType, area: Area): Int =
		effects.firstOrNull { it.area == area && it.type == type }?.value
			?: 0
	fun getAffectedAreas(type: ActionType): List<Area> =
		effects.filter { it.type == type }.map { it.area }

	override fun toString() = "Move: $name; Effects: " + effects.joinToString(", ")
}
