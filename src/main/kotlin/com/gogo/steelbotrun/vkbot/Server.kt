package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.battle.Fight
import com.gogo.steelbotrun.vkbot.battle.actions.ActionType
import com.gogo.steelbotrun.vkbot.battle.actions.Area
import com.gogo.steelbotrun.vkbot.battle.actions.Move
import com.gogo.steelbotrun.vkbot.battle.actions.SimpleAction
import com.gogo.steelbotrun.vkbot.battle.fighters.Fighter
import com.gogo.steelbotrun.vkbot.battle.fighters.Player
import com.gogo.steelbotrun.vkbot.battle.fighters.monsters.BasicMonster
import com.gogo.steelbotrun.vkbot.command.Message
import com.gogo.steelbotrun.vkbot.event.Event
import com.gogo.steelbotrun.vkbot.event.EventMessage
import com.gogo.steelbotrun.vkbot.request.Request
import com.gogo.steelbotrun.vkbot.request.RequestBuilder
import com.gogo.steelbotrun.vkbot.sdk.SDK
import org.springframework.stereotype.Component

@Component
class Server (
	val fights: MutableList<Fight> = mutableListOf(),
	val pendingRequests: MutableList<Request> = mutableListOf(),
	val players: HashMap<Int, Player> = hashMapOf()
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
				SDK.send("Request is successfully created", event.info.fromId)
				println(pendingRequests)
			}
			event.text.contains("dungeon") -> {
				println(fights)
				fights.add(Fight(mutableListOf(players[event.info.fromId]!!, BasicMonster("mob",
						listOf(Area.Head, Area.Body, Area.Legs),
						listOf(
								Move("Nitoryu: Dragon's Fangs", listOf(
										SimpleAction(ActionType.Dodge, Area.Head, 100),
										SimpleAction(ActionType.Attack, Area.Body, 10),
										SimpleAction(ActionType.Penalty, Area.Legs, 5)
								)),
								Move("Block", listOf(
										SimpleAction(ActionType.Block, Area.Body, 20)
								))
						)))))
				println(fights)
			}

			event.text.contains("create") -> {
				players[event.info.fromId] = Player("YAYA", event.info.fromId.toString())
				println(players)
			}
		}
		if (event.info.payload != null) {}
		return ""
	}

//	fun changeName(playerId: String, name: String) {
//		players[playerId] = name
//		fights.forEach { it.participants.firstOrNull { it is Player && it.id == playerId }?.name = name }
//	}

	fun findFight(playerId: String) = fights.firstOrNull { it.participants.filter { it is Player && it.id == playerId }.isNotEmpty() }
}
