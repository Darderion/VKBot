package com.gogo.steelbotrun.vkbot.battle

import com.gogo.steelbotrun.vkbot.Random
import com.gogo.steelbotrun.vkbot.battle.actions.Move
import com.gogo.steelbotrun.vkbot.battle.actions.ActionType.*
import com.gogo.steelbotrun.vkbot.battle.actions.Area.*
import com.gogo.steelbotrun.vkbot.battle.actions.SimpleAction
import com.gogo.steelbotrun.vkbot.battle.fighters.Fighter
import com.gogo.steelbotrun.vkbot.battle.fighters.Player
import com.gogo.steelbotrun.vkbot.battle.fighters.monsters.BasicMonster
import com.gogo.steelbotrun.vkbot.battle.fighters.monsters.Monster

class Fight(participants: MutableList<Fighter>) {
	val participants: MutableList<Fighter> = mutableListOf()

	private var step = 1

	init {
		this.participants.addAll(participants)
	}

	fun update(): String {
		if (participants.count() != 2) return "Fight contains ${participants.count()} participants"

		val fightersChoosingMoves = participants.filter { it.selectedMove == null }
		if (fightersChoosingMoves.isNotEmpty()) {
			return "Fighter ${fightersChoosingMoves.first().name} is still choosing a move"
		}

		var round = "Round ${step}:\n"

		val pairs = listOf(
			Pair(participants[0], participants[1]),
			Pair(participants[1], participants[0])
		)

		for (attackerAndDefender in pairs) {
			var damage = 0

			val attacker = attackerAndDefender.first
			val defender = attackerAndDefender.second

			val attackingMove = attacker.selectedMove
			val defendingMove = defender.selectedMove

			for (bodyPart in defender.bodyParts) {
				if (Random.roll(defendingMove!!.getEffect(Dodge, bodyPart)))
					continue

				var attack = attackingMove!!.getEffect(Attack, bodyPart) - defendingMove.getEffect(Block, bodyPart)
				if (attack > 0) {
					attack += defendingMove.getEffect(Penalty, bodyPart)
					damage += attack
				}
			}

			round += "${attacker.name} casts ${attacker.selectedMove!!.name} and deals ${if (damage > 0) damage else "no"} damage\n"

			defender.hp = defender.hp - damage
		}

		participants.forEach {
			it.nextRound()
		}
		step++
		return round
	}
}
