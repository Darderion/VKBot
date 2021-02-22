package com.gogo.steelbotrun.vkbot

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.Error

class JSON {
	private val jsonNode: JsonNode

	constructor(jsonText: String) {
		val mapper = ObjectMapper()
		jsonNode = mapper.readTree(jsonText)
			?: throw Error("Failed to read json")
	}

	constructor(jsonNode: JsonNode) {
		this.jsonNode = jsonNode
	}

	fun getJSON(jsonPath: String): JSON {
		if (jsonPath.contains("/")) {
			val jsonText = jsonPath.split("/")
			return this.getJSON(jsonText.first()).getJSON(jsonPath.substring(jsonPath.indexOf("/") + 1))
		}
		val jsonBody = jsonNode.get(jsonPath)
			?: throw Error("No node named ${jsonPath} found")
		return JSON(jsonBody)
	}

	fun get(propertyName: String) = jsonNode.get(propertyName)?.toString()?.replace("\"", "")
		?: throw Error("No text found")

	fun getInt(propertyName: String): Int {
		val jsonProperty = jsonNode.get(propertyName)
			?: throw Error("No ${propertyName} found")
		val propertyValue = if (jsonProperty.isNumber) jsonProperty.intValue()
		else throw Error("${propertyName} is not a number")

		return propertyValue
	}
}
