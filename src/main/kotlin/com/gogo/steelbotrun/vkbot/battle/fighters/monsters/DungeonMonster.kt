package com.gogo.steelbotrun.vkbot.battle.fighters.monsters

import com.gogo.steelbotrun.vkbot.battle.actions.Area
import com.gogo.steelbotrun.vkbot.battle.actions.Move
import kotlin.random.Random

class DungeonMonster(name: String, bodyParts: List<Area>, moves: List<Move>):
	Monster(name, bodyParts, moves) {
	override fun chooseMove() = moves[Random.nextInt(0, moves.size)]
}