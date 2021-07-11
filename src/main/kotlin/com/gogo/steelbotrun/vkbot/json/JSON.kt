package com.gogo.steelbotrun.vkbot.json

abstract class JSON {
	constructor(jsonText: String)

	abstract fun getJSON(jsonPath: String): JSON

	abstract fun get(propertyName: String): String

	abstract fun getInt(propertyName: String): Int
}
