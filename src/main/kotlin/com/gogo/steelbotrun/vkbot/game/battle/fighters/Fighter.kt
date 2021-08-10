package com.gogo.steelbotrun.vkbot.game.battle.fighters

import com.gogo.steelbotrun.vkbot.game.moves.Move
import com.gogo.steelbotrun.vkbot.game.battle.actions.Area

abstract class Fighter(
	var name: String,
	val bodyParts: List<Area>,
	val moves: List<Move>) {

	var hp = 100
	var selectedMove: Move? = null

	/**
	 * Returns a move if and only if exactly one move in a fighter's moveset contains argument NAME in it's name
	 */
	fun findMove(name: String): Move? {
		val moveName = name.toLowerCase()
		val moves = this.moves.filter { it.name.toLowerCase().contains(moveName) }
		if (moves.count() != 1) return null
		return moves.first()
	}

	/**
	 * Selects a move which contains argument NAME in it's name
	 * Returns true if a move was found and false otherwise
	 */
	fun selectMove(name: String): Boolean {
		selectedMove = findMove(name)
		return (selectedMove != null)
	}

	open fun nextRound() {
		selectedMove = null
	}
}