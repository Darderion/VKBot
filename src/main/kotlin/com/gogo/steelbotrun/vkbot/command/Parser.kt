package com.gogo.steelbotrun.vkbot.command

import com.gogo.steelbotrun.vkbot.command.Parser.State.*
import com.gogo.steelbotrun.vkbot.command.TokenType.*

class Parser {
	enum class State {
		CommandDeclaration,
		CommandStack
	}

	companion object {
		private val stateMachine: HashMap<Pair<State, TokenType>, State> = hashMapOf(
			Pair(CommandDeclaration, TokenType.Command) to CommandStack,
			Pair(CommandStack, TokenType.Argument) to CommandStack,
			Pair(CommandStack, NewLine) to CommandDeclaration
		)

		fun getCommands(message: Message): List<Command> {
			if (!check(message)) throw Error("Incorrect message body")

			val commands: MutableList<Command> = mutableListOf()

			val command: MutableList<Token> = mutableListOf()
			for(token in message.tokens) {
				if (token.type == NewLine) {
					if (command.isNotEmpty()) {
						commands.add(Command(command))
					}
					command.clear()
				} else {
					command.add(token)
				}
			}
			return commands
		}

		fun check(message: Message): Boolean {
			var currentState = CommandDeclaration

			for (token in message.tokens) {
				val currentPair = Pair(currentState, token.type)
				if (!stateMachine.containsKey(currentPair)) {
					return false
				}
				currentState = stateMachine[currentPair]!!
			}

			return true
		}
	}
}