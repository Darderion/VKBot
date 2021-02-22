package com.gogo.steelbotrun.vkbot.command

class Lexer {
	companion object {
		private fun String.replaceNewLineTokens() = replace(",", " , ")

		fun getTokens(text: String): List<Token> {
			if (text.isBlank()) return listOf()

			val text = text.replaceNewLineTokens().trim().split(" ").filter { it.isNotBlank() }
			var previousToken: Token? = null
			val tokens: MutableList<Token> = mutableListOf()
			for(token in text) {
				previousToken = Token(token, if (token == ",") TokenType.NewLine else
					when(previousToken) {
						null -> TokenType.Command
						else -> when(previousToken.type) {
							TokenType.NewLine -> TokenType.Command
							else -> TokenType.Argument
						}
					})
				tokens.add(previousToken)
			}
			return tokens
		}
	}
}
