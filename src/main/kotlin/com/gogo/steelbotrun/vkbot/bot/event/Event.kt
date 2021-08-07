package com.gogo.steelbotrun.vkbot.bot.event

import com.gogo.steelbotrun.vkbot.utils.json.JSON
import com.gogo.steelbotrun.vkbot.utils.json.JSONJackson

open class Event(receivedData: String) {
	val type: String
	val json: JSON

	init {
		json = JSONJackson(receivedData)
		type = json.get("type")
	}

	open fun response(): String? = null
}
