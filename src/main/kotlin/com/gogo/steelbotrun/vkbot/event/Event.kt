package com.gogo.steelbotrun.vkbot.event

import com.gogo.steelbotrun.vkbot.JSON

open class Event(receivedData: String) {
	val type: String
	val json: JSON

	init {
		json = JSON(receivedData)
		type = json.get("type")
	}

	open fun response(): String? = null
}
