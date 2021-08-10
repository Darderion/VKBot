package com.gogo.steelbotrun.vkbot.utils.json

import com.google.gson.JsonObject
import com.google.gson.JsonParser

class JSONGoogle : JSON {
	// Google json object
	private val jsonNode: JsonObject

	constructor(jsonText: String) : super(jsonText) {
		jsonNode = JsonParser.parseString(jsonText).asJsonObject
			?: throw Error("Failed to read json")
	}

	constructor(jsonNode: JsonObject) : super("") {
		this.jsonNode = jsonNode
	}

	override fun getJSON(jsonPath: String): JSON {
		if (jsonPath.contains("/")) {
			val jsonText = jsonPath.split("/")
			return this.getJSON(jsonText.first())
				.getJSON(jsonPath.substring(jsonPath.indexOf("/") + 1))
		}
		val jsonBody = jsonNode.getAsJsonObject(jsonPath)
			?: throw Error("No node named $jsonPath found")
		return JSONGoogle(jsonBody)
	}

	override fun get(propertyName: String) =
		jsonNode.get(propertyName)?.toString()?.replace("\"", "")
		?: throw Error("No text found")

	override fun getInt(propertyName: String): Int {
		val jsonProperty = jsonNode.get(propertyName)
			?: throw Error("No $propertyName found")

		return if (jsonProperty.isJsonPrimitive && jsonProperty.asJsonPrimitive.isNumber)
			jsonProperty.asInt
		else throw Error("$propertyName is not a number")
	}

	override fun contains(jsonPath: String): Boolean {
		if (jsonPath.contains("/")) {
			val jsonText = jsonPath.split("/")
			return this.getJSON(jsonText.first())
				.contains(jsonPath.substring(jsonPath.indexOf("/") + 1))
		}
		return jsonNode.has(jsonPath)
	}
}