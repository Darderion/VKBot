package com.gogo.steelbotrun.vkbot.battle.actions

import com.gogo.steelbotrun.vkbot.character.stats.Stats

class Move(val name: String, private val effects: List<SimpleAction>, val description: String = "") {
	/**
	 * Get effect of type TYPE that affects area AREA with stats STATS
	 * @param type Type of action
	 * @param area Affected area
	 * @param stats Stats to apply to stats' coefficients
	 * @return Value corresponding to effect
	 */
	fun getEffect(type: ActionType, area: Area, stats: Stats = Stats()): Double {
		val effect = effects.firstOrNull { it.area == area && it.type == type }
		return if (effect != null) effect.value + (effect.statScaling * stats).sum else 0.0
	}

	/**
	 * Get areas affected by effects of type TYPE
	 * @param type Type of action
	 * @return List of areas
	 */
	fun getAffectedAreas(type: ActionType): List<Area> =
		effects.filter { it.type == type }.map { it.area }

	override fun toString() = "Move: $name; Effects: " + effects.joinToString(", ")
}
