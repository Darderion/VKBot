package com.gogo.steelbotrun.vkbot.battle.fighters.monsters

import com.gogo.steelbotrun.vkbot.battle.actions.Move
import com.gogo.steelbotrun.vkbot.battle.actions.Area
import com.gogo.steelbotrun.vkbot.battle.fighters.Fighter

abstract class Monster(name: String, bodyParts: List<Area>, moves: List<Move>):
	Fighter(name, bodyParts, moves)
{
	init {
		selectMove()
	}

	abstract fun chooseMove(): Move

	fun selectMove() {
		selectedMove = chooseMove()
	}

	override fun nextRound() {
		selectMove()
	}
}
