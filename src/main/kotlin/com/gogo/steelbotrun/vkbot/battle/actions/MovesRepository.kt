package com.gogo.steelbotrun.vkbot.battle.actions

import java.io.File
import java.lang.Error

class MovesRepository(filePath: String = "/src/main/resources/static/moves.txt") {
	private val moves = mutableListOf<Move>()

	fun moves() = moves.toList()

	init {
		val movesFile = File("${System.getProperty("user.dir")}$filePath").readLines()
		val movesText = movesFile.map { it.trim() }.filter { it.isNotEmpty() }.filter { it[0] != '#' } + ":"

		var moveName = ""
		val moveActions = mutableListOf<SimpleAction>()

		movesText.forEach {
			if (it.contains(":")) {
				if (moveActions.count() != 0) {
					moves.add(Move(moveName, moveActions))
				}
				moveName = it.replace(":", "")
			} else {
				val moveAction = it.split(" ", "\t").map { it.trim() }.filter { it.isNotEmpty() }

				if (moveAction.count() != 3) throw Error("\"$it\" move action contains K spaces, K != 2, K = ${moveAction.count()}")

				moveActions.add(
					SimpleAction(
						ActionType.valueOf(toEnumString(moveAction[0])),
						Area.valueOf(toEnumString(moveAction[1])),
						moveAction[2].toInt()
					)
				)
			}
		}
	}

	private fun toEnumString(enumValue: String) = enumValue.toLowerCase().capitalize()
}
