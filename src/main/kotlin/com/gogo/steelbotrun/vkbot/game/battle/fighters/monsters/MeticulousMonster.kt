package com.gogo.steelbotrun.vkbot.game.battle.fighters.monsters

import com.gogo.steelbotrun.vkbot.game.battle.actions.Area
import com.gogo.steelbotrun.vkbot.game.moves.Move

class MeticulousMonster(name: String, bodyParts: List<Area>, moves: List<Move>):
	Monster(name, bodyParts, moves) {
	var moveIndex = -1
	override fun chooseMove(): Move {
		moveIndex = (moveIndex + 1) % moves.size
		return moves[moveIndex]
	}
}