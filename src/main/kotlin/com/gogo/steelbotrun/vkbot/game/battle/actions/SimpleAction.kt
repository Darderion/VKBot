package com.gogo.steelbotrun.vkbot.game.battle.actions

import com.gogo.steelbotrun.vkbot.game.character.stats.Stats

data class SimpleAction(val type: ActionType, val area: Area, val value: Double, val statScaling: Stats = Stats()) {
	override fun toString() = "$type $area $value"
}
