package com.gogo.steelbotrun.vkbot.request

import com.gogo.steelbotrun.vkbot.Server
import com.gogo.steelbotrun.vkbot.battle.Fight
import com.gogo.steelbotrun.vkbot.command.Message

class RequestDuel(message: Message) : Request(message) {

	override fun processing(server: Server) {
		//need check for fights
		server.fights.add(Fight(mutableListOf()))
	}
}
