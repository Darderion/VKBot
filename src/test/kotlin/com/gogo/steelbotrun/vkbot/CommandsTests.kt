package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.command.Lexer
import com.gogo.steelbotrun.vkbot.command.Message
import com.gogo.steelbotrun.vkbot.command.Parser
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeExactly

class CommandsTests : StringSpec({
	"Lexer should return correct tokens" {
		Lexer.getTokens("").count() shouldBeExactly 0
		Lexer.getTokens("Token").count() shouldBeExactly 1
		Lexer.getTokens("  Token    SomeOtherToken").count() shouldBeExactly 2
		Lexer.getTokens(" Token   Token2  Token3    ").count() shouldBeExactly 3
	}
	"Parser should return correct commands" {
		val message = Message(Lexer.getTokens("Function Argument1"))
		Parser.getCommands(message).count() shouldBeExactly 1

		val message2 = Message(Lexer.getTokens("Function Argument1, Function2 Argument2"))
		Parser.getCommands(message2).count() shouldBeExactly 2
	}
})