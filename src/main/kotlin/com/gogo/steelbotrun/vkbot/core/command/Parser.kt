package com.gogo.steelbotrun.vkbot.core.command

import com.gogo.steelbotrun.vkbot.core.command.Parser.State.*
import com.gogo.steelbotrun.vkbot.core.command.TokenType.*

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

		fun getCommands(tokens: List<Token>): List<Command> {
			val message = if (tokens[tokens.count() - 1].type != TokenType.NewLine) {
				tokens + Token(",", TokenType.NewLine)
			} else {
				tokens
			}

			if (!check(message)) throw Error("Incorrect message body")

			val commands: MutableList<Command> = mutableListOf()

			val command: MutableList<Token> = mutableListOf()
			for(token in message) {
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

		private fun check(tokens: List<Token>): Boolean {
			var currentState = CommandDeclaration

			for (token in tokens) {
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