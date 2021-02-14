package com.gogo.steelbotrun.vkbot.events

import com.gogo.steelbotrun.vkbot.*
import com.gogo.steelbotrun.vkbot.battle.Fight
import com.gogo.steelbotrun.vkbot.battle.actions.ActionType
import com.gogo.steelbotrun.vkbot.battle.actions.Area
import com.gogo.steelbotrun.vkbot.battle.actions.Move
import com.gogo.steelbotrun.vkbot.battle.actions.SimpleAction
import com.gogo.steelbotrun.vkbot.battle.fighters.Player
import com.gogo.steelbotrun.vkbot.battle.fighters.monsters.BasicMonster
import com.gogo.steelbotrun.vkbot.battle.fighters.monsters.Monster
import java.util.*

class EventMessage(val console: Console, receivedData: String): Event(receivedData) {
	var date: String
	var fromId: String
	var peerId: String
	var text: String
	var id: String

	init {
		val jsonBody = json.get("object")
			?: throw Exception("Failed to read json body")
		val jsonInfo = jsonBody.get("message")
			?: throw Exception("Failed to read json info")
		when (type) {
			"message_new" -> {
				if (!(jsonInfo.has("date")
							&& jsonInfo.has("from_id")
							&& jsonInfo.has("peer_id")
							&& jsonInfo.has("text")
							&& jsonInfo.has("id")
							)
				) {
					throw Exception("Invalid message body")
				}
				id = jsonInfo.get("id").toString()
				date = jsonInfo.get("date").toString()
				fromId = jsonInfo.get("from_id").toString()
				peerId = jsonInfo.get("peer_id").toString()
				text = jsonInfo.get("text").toString().replace("\"", "")
			}
			else -> {
				throw Exception("Unknown message type")
			}
		}
	}

	override fun process() {
		console.process(this)
		// println("${url}: $response")
	}
}
