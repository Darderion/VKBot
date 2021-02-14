package com.gogo.steelbotrun.vkbot.battle.fighters.monsters

import com.gogo.steelbotrun.vkbot.battle.actions.Area
import com.gogo.steelbotrun.vkbot.battle.actions.Move
import kotlin.random.Random

class MeticulousMonster(name: String, bodyParts: List<Area>, moves: List<Move>):
	Monster(name, bodyParts, moves) {
	var moveIndex = -1
	override fun chooseMove(): Move {
		moveIndex = (moveIndex + 1) % moves.size
		return moves[moveIndex]
	}
}