package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.battle.Fight
import com.gogo.steelbotrun.vkbot.battle.fighters.Player
import com.gogo.steelbotrun.vkbot.command.CommandType
import com.gogo.steelbotrun.vkbot.command.Lexer
import com.gogo.steelbotrun.vkbot.command.Message
import com.gogo.steelbotrun.vkbot.command.Parser
import com.gogo.steelbotrun.vkbot.event.Event
import com.gogo.steelbotrun.vkbot.event.EventMessage
import com.gogo.steelbotrun.vkbot.request.Request
import com.gogo.steelbotrun.vkbot.request.RequestBuilder
import com.gogo.steelbotrun.vkbot.sdk.SDK
import org.springframework.stereotype.Component

@Component
class Server (
	val fights: MutableList<Fight> = mutableListOf(),
	val requests: MutableList<Request> = mutableListOf(),
	val players: HashMap<String, String> = hashMapOf(),
	private val sdk: SDK = SDK()
) {
	private val log = mutableListOf("Server log")
	private fun log(text: String) {
		log.add(text)
	}
	fun log() {
		log.forEach { println(it) }
	}

	fun process(event: Event): String {
		val response = event.response()
		if (response != null) return response

		return when(event) {
			is EventMessage -> process(event)
			else -> "Unknown event type: ${event.type}"
		}
	}

	private fun process(event: EventMessage): String {
		val commands = Parser.getCommands(Lexer.getTokens(event.text))

		commands.forEach {
			when(it.type) {
				CommandType.Duel -> {
					log("Player ${event.info.fromId} sent duel request")
					requests.add(RequestBuilder.createRequest(Message(event.info, event.text)))
					sdk.send("Ваш запрос успешно создан", event.info.fromId)
				}
			}
		}

		return "OK"
	}

	fun changeName(playerId: String, name: String): Boolean {
		if (!players.containsKey(playerId)) {
			return false
		}

		players[playerId] = name
		fights.forEach { it.participants.firstOrNull { it is Player && it.id == playerId }?.name = name }

		return true
	}

	fun findFight(playerId: String) = fights.firstOrNull { it.participants.filter { it is Player && it.id == playerId }.isNotEmpty() }
}
