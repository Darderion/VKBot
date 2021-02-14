package com.gogo.steelbotrun.vkbot.events

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.gogo.steelbotrun.vkbot.Server

open class Event(receivedData: String) {
	val type: String
	val json: JsonNode

	init {
		val mapper = ObjectMapper()
		json = mapper.readTree(receivedData)
			?: throw Exception("Failed to read json")
		val jsonType = json.get("type")
			?: throw Exception("Failed to get json message's type")
		type = jsonType.toString().replace("\"", "").trim()
	}

	open fun response(): String? = null

	open fun process() {
	}
}
