package com.gogo.steelbotrun.vkbot.core.command

import com.gogo.steelbotrun.vkbot.bot.event.EventMessage
import com.gogo.steelbotrun.vkbot.bot.event.MessageInfo

class Message(val info: MessageInfo, text: String) {
	val commands: List<Command> = Parser.getCommands(Lexer.getTokens(text))

	constructor(event: EventMessage) : this(event.info, event.text)
}
