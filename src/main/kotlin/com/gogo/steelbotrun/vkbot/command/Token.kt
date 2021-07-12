package com.gogo.steelbotrun.vkbot.command

enum class TokenType {
	Command, Argument, NewLine
}

class Token(val text: String, val type: TokenType) {
	companion object {
		val NewLineTokens = listOf(",", ";")
		val Delimiters = listOf(" ")
	}
}
