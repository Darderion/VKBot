package com.gogo.steelbotrun.vkbot.command

import com.gogo.steelbotrun.vkbot.event.MessageInfo

class Message(val info: MessageInfo, text: String) {
	val commands: List<Command> = Parser.getCommands(Lexer.getTokens(text))
}