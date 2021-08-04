package com.gogo.steelbotrun.vkbot.battle.actions

data class SimpleAction(val type: ActionType, val area: Area, val value: Int) {
	override fun toString() = "$type $area $value"
}
