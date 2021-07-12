package com.gogo.steelbotrun.vkbot.json

abstract class JSON(jsonText: String) {

	abstract fun getJSON(jsonPath: String): JSON

	abstract fun get(propertyName: String): String

	abstract fun getInt(propertyName: String): Int

	abstract fun contains(jsonPath: String): Boolean
}
