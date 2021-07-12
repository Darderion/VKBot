package com.gogo.steelbotrun.vkbot.command

import com.gogo.steelbotrun.vkbot.command.CommandType.*
import java.lang.Error

enum class CommandType {
	Attack, Fight, ChangeAccountName
}

class Command(tokens: List<Token>) {
	val type: CommandType
	val arguments: List<String>

	init {
		val commandList = tokens.filter { it.type == TokenType.Command }
		if (commandList.isEmpty()) throw Error("No command found")
		if (commandList.count() > 1) throw Error("Too many commands found")

		val command = commandList.first()
		type = if (command.text == "attack") Attack else Fight

		arguments = tokens.filter { it != command }.map { it.text }
		if (!checkArgumentsCount(type, arguments)) throw Error("Incorrect number of arguments")
	}

	companion object {
		fun checkArgumentsCount(command: CommandType, arguments: List<String>) = when(command) {
			Attack -> arguments.count() == 1
			Fight -> arguments.count() == 1
			ChangeAccountName -> arguments.count() == 1
		}
	}
}