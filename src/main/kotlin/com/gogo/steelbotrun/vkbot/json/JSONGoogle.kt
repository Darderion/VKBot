package com.gogo.steelbotrun.vkbot.json

import com.google.gson.JsonObject
import com.google.gson.JsonParser

class JSONGoogle : JSON {
	// Google json object
	private val jsonNode: JsonObject

	constructor(jsonText: String) : super(jsonText) {
		val parser = JsonParser()
		jsonNode = parser.parse(jsonText).asJsonObject
			?: throw Error("Failed to read json")
	}

	constructor(jsonNode: JsonObject) : super("") {
		this.jsonNode = jsonNode
	}

	override fun getJSON(jsonPath: String): JSON {
		if (jsonPath.contains("/")) {
			val jsonText = jsonPath.split("/")
			return this.getJSON(jsonText.first()).getJSON(jsonPath.substring(jsonPath.indexOf("/") + 1))
		}
		val jsonBody = jsonNode.getAsJsonObject(jsonPath)
			?: throw Error("No node named ${jsonPath} found")
		return JSONGoogle(jsonBody)
	}

	override fun get(propertyName: String) = jsonNode.get(propertyName)?.toString()?.replace("\"", "")
		?: throw Error("No text found")

	override fun getInt(propertyName: String): Int {
		val jsonProperty = jsonNode.get(propertyName)
			?: throw Error("No ${propertyName} found")
		val propertyValue = if (jsonProperty.isJsonPrimitive && jsonProperty.asJsonPrimitive.isNumber) jsonProperty.asInt
		else throw Error("${propertyName} is not a number")

		return propertyValue
	}
}