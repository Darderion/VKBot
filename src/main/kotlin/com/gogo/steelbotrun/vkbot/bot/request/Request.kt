package com.gogo.steelbotrun.vkbot.bot.request

import com.gogo.steelbotrun.vkbot.Server
import com.gogo.steelbotrun.vkbot.core.command.Message

abstract class Request(val message: Message) {
	val ready = false
	abstract fun processing(server: Server)
}
