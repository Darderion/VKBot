package com.gogo.steelbotrun.vkbot.bot.request

import com.gogo.steelbotrun.vkbot.Server
import com.gogo.steelbotrun.vkbot.game.battle.Fight
import com.gogo.steelbotrun.vkbot.core.command.Message

class RequestDuel(message: Message) : Request(message) {

	override fun processing(server: Server) {
		//need check for fights
		server.fights.add(Fight(mutableListOf()))
	}
}
