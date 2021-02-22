package com.gogo.steelbotrun.vkbot.command

class Message(tokens: List<Token>) {
	val tokens: List<Token>
	init {
		this.tokens = if (tokens[tokens.count() - 1].type != TokenType.NewLine) {
			tokens + Token(",", TokenType.NewLine)
		} else {
			tokens
		}
	}
}