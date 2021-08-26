package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.game.battle.Fight
import com.gogo.steelbotrun.vkbot.game.battle.fighters.Player
import com.gogo.steelbotrun.vkbot.core.command.CommandType
import com.gogo.steelbotrun.vkbot.core.command.Message
import com.gogo.steelbotrun.vkbot.bot.event.Event
import com.gogo.steelbotrun.vkbot.bot.event.EventMessage
import com.gogo.steelbotrun.vkbot.bot.request.Request
import com.gogo.steelbotrun.vkbot.bot.request.RequestFactory
import com.gogo.steelbotrun.vkbot.bot.sdk.SDK
import com.gogo.steelbotrun.vkbot.game.account.AccountsRepository
import com.gogo.steelbotrun.vkbot.utils.logger.Logger
import org.springframework.stereotype.Component

@Component
class Server (
	val fights: MutableList<Fight> = mutableListOf(),
	val requests: MutableList<Request> = mutableListOf(),
	private val accounts: AccountsRepository = AccountsRepository(),
	private val sdk: SDK = SDK(),
	val log: Logger = Logger()
) {
	fun process(event: Event): String {
		val response = event.response()
		if (response != null) return response

		return when (event) {
			is EventMessage -> process(Message(event))
			else -> "Unknown event type: ${event.type}"
		}
	}

	private fun process(message: Message): String {
		message.commands.forEach {
			when (it.type) {
				CommandType.Duel -> {
					log write "Player ${message.info.fromId} sent duel request"
					requests.add(RequestFactory.createRequest(message))
					sdk.send("Ваш запрос успешно создан", message.info.fromId)
				}
			}
		}
		return "OK"
	}
}
