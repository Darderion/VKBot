package com.gogo.steelbotrun.vkbot.event

import com.gogo.steelbotrun.vkbot.json.JSON
import com.gogo.steelbotrun.vkbot.json.JSONJackson

open class Event(receivedData: String) {
	val type: String
	val json: JSON

	init {
		json = JSONJackson(receivedData)
		type = json.get("type")
	}

	open fun response(): String? = null
}
