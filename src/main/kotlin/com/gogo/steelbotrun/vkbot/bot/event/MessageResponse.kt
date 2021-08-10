package com.gogo.steelbotrun.vkbot.bot.event

enum class MessageResponseType {
	All, None, Sender
}

class MessageResponse(val text: String, sendTo: MessageResponseType) {
}
