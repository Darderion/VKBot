package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.battle.Fight
import com.gogo.steelbotrun.vkbot.battle.fighters.Player
import org.springframework.stereotype.Component

@Component
class Server(
	val fights: MutableList<Fight> = mutableListOf(Fight(mutableListOf())),
	val players: HashMap<String, String> = hashMapOf()
) {
	fun changeName(playerId: String, name: String) {
		players[playerId] = name
		fights.forEach { it.participants.firstOrNull { it is Player && it.id == playerId }?.name = name }
	}

	fun findFight(playerId: String) = fights.firstOrNull { it.participants.filter { it is Player && it.id == playerId }.isNotEmpty() }
}
