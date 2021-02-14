package com.gogo.steelbotrun.vkbot.battle.fighters.monsters

import com.gogo.steelbotrun.vkbot.battle.actions.Move
import com.gogo.steelbotrun.vkbot.battle.actions.Area

class BasicMonster(name: String, bodyParts: List<Area>, moves: List<Move>):
	Monster(name, bodyParts, moves) {
	override fun chooseMove() = moves.first()
}