package com.gogo.steelbotrun.vkbot.game.battle

import com.gogo.steelbotrun.vkbot.utils.random.Random
import com.gogo.steelbotrun.vkbot.game.battle.actions.ActionType.*
import com.gogo.steelbotrun.vkbot.game.battle.fighters.Fighter
import com.gogo.steelbotrun.vkbot.bot.event.MessageResponse
import com.gogo.steelbotrun.vkbot.bot.event.MessageResponseType.*
import kotlin.math.roundToInt

class Fight(participants: MutableList<Fighter>) {
	val participants: MutableList<Fighter> = mutableListOf()

	private var step = 1

	init {
		this.participants.addAll(participants)
	}


	/**
	 * Update battle round by checking ready state of participants and performing combat
	 * @return Response
	 */
	fun update(): MessageResponse {
		if (participants.count() != 2) return MessageResponse(
			"Fight contains ${participants.count()} participants",
			Sender
		)

		val fightersChoosingMoves = participants.filter { it.selectedMove == null }
		if (fightersChoosingMoves.isNotEmpty()) {
			return MessageResponse(
				"Fighter ${fightersChoosingMoves.first().name} is still choosing a move",
				Sender
			)
		}

		var round = "Round ${step}:\n"

		val pairs = listOf(
			Pair(participants[0], participants[1]),
			Pair(participants[1], participants[0])
		)

		for (attackerAndDefender in pairs) {
			var damage = 0.0

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

			defender.hp = (defender.hp - damage).roundToInt()
		}

		participants.forEach {
			it.nextRound()
		}
		step++
		return MessageResponse(round, All)
	}
}
