package com.gogo.steelbotrun.vkbot.request

import com.gogo.steelbotrun.vkbot.Server
import com.gogo.steelbotrun.vkbot.command.Message

abstract class Request(val message: Message) {
	val ready = false
	abstract fun processing(server: Server)
}
