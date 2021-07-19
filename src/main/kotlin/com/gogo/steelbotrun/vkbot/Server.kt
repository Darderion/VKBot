package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.battle.Fight
import com.gogo.steelbotrun.vkbot.battle.fighters.Player
import com.gogo.steelbotrun.vkbot.command.Message
import com.gogo.steelbotrun.vkbot.event.Event
import com.gogo.steelbotrun.vkbot.event.EventMessage
import com.gogo.steelbotrun.vkbot.request.Request
import com.gogo.steelbotrun.vkbot.request.RequestBuilder
import com.gogo.steelbotrun.vkbot.sdk.SDK
import org.springframework.stereotype.Component

@Component
class Server (
	val fights: MutableList<Fight> = mutableListOf(Fight(mutableListOf())),
	val pendingRequests: MutableList<Request> = mutableListOf(),
	val players: HashMap<String, String> = hashMapOf()
) {
	fun process(event: Event): String {
		val response = event.response()
		if (response != null) return response

		return when(event) {
			is EventMessage -> process(event)
			else -> "Unknown event type: ${event.type}"
		}
	}

	fun process(event: EventMessage): String {
		when {
			event.text.contains("duel") -> {
				pendingRequests.add(RequestBuilder.createRequest(Message(event.info, event.text)))
				SDK.send("Ваш запрос успешно создан", event.info.fromId)
			}
		}

		if (event.info.payload != null) {}

		return ""
	}

	fun changeName(playerId: String, name: String) {
		players[playerId] = name
		fights.forEach { it.participants.firstOrNull { it is Player && it.id == playerId }?.name = name }
	}

	fun findFight(playerId: String) = fights.firstOrNull { it.participants.filter { it is Player && it.id == playerId }.isNotEmpty() }
}
