package com.gogo.steelbotrun.vkbot.command

import com.gogo.steelbotrun.vkbot.command.CommandType.*

enum class CommandType {
	Attack, Fight, ChangeAccountName, Greeting, Duel, Error
}

class Command(tokens: List<Token>) {
	val type: CommandType
		get() = if (isCorrect) field else Error
	val arguments: List<String>
	val isCorrect: Boolean
	val errorMessage: String

	init {
		var error = ""
		val commandList = tokens.filter { it.type == TokenType.Command }
		if (commandList.isEmpty()) error += "No command found; "
		if (commandList.count() > 1) error += "Too many commands found; "

		val command = commandList.first()

		type = when(command.text) {
			"attack" -> Attack
			"duel" -> Duel
			else -> { error += "Incorrect command type; "; Error }
		}

		arguments = tokens.filter { it != command }.map { it.text }
		if (!checkArgumentsCount(type, arguments)) error += "Incorrect number of arguments; "

		isCorrect = (error.isEmpty())
		errorMessage = error
	}

	companion object {
		fun checkArgumentsCount(command: CommandType, arguments: List<String>) = when(command) {
			Attack -> arguments.count() == 1
			Fight -> arguments.count() == 1
			ChangeAccountName -> arguments.count() == 1
			Greeting -> arguments.count() == 0
			Duel -> arguments.count() == 1
			else -> true
		}
	}
}