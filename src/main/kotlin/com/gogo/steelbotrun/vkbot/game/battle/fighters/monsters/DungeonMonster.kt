package com.gogo.steelbotrun.vkbot.game.battle.fighters.monsters

import com.gogo.steelbotrun.vkbot.game.battle.actions.Area
import com.gogo.steelbotrun.vkbot.game.moves.Move
import kotlin.random.Random

class DungeonMonster(name: String, bodyParts: List<Area>, moves: List<Move>):
	Monster(name, bodyParts, moves) {
	override fun chooseMove() = moves[Random.nextInt(0, moves.size)]
}
