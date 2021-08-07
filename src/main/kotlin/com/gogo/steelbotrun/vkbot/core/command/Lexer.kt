package com.gogo.steelbotrun.vkbot.core.command

class Lexer {
	companion object {
		fun String.replaceNewLineTokens() = this.replaceNewLineTokens(0)

		private fun String.replaceNewLineToken(token: String) = this.replace(token, "${Token.Delimiters.first()}${token}${Token.Delimiters.first()}")

		private fun String.replaceNewLineTokens(k: Int): String {
			return if (k + 1 < Token.NewLineTokens.count())
				replaceNewLineToken(Token.NewLineTokens[k]).replaceNewLineTokens(k + 1) else
				replaceNewLineToken(Token.NewLineTokens[k])
		}

		fun getTokens(text: String): List<Token> {
			if (text.isBlank()) return listOf()

			val text = text.replaceNewLineTokens().trim().split(Token.Delimiters.first()).filter { it.isNotBlank() }

			var previousToken: Token? = null
			val tokens: MutableList<Token> = mutableListOf()
			for(token in text) {
				previousToken = Token(token, if (Token.NewLineTokens.contains(token)) TokenType.NewLine else
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
