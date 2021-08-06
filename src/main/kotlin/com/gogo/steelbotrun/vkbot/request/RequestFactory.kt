package com.gogo.steelbotrun.vkbot.request

import com.gogo.steelbotrun.vkbot.command.Message

class RequestFactory {
	companion object {
		fun createRequest(message: Message) = when {
			//need check for requests
			message.commands.first().type.toString().toLowerCase().contains("duel") -> RequestDuel(message)
			else -> throw Exception("No one request match this message")
		}
	}
}
