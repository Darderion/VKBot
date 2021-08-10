package com.gogo.steelbotrun.vkbot.game.battle.fighters.monsters

import com.gogo.steelbotrun.vkbot.game.moves.Move
import com.gogo.steelbotrun.vkbot.game.battle.actions.Area

class BasicMonster(name: String, bodyParts: List<Area>, moves: List<Move>):
	Monster(name, bodyParts, moves) {
	override fun chooseMove() = moves.first()
}