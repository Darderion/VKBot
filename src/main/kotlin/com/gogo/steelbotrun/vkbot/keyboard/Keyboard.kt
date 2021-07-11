package com.gogo.steelbotrun.vkbot.keyboard

import com.google.gson.Gson

// Variables are converted to JSON and therefore their names need to be preserved
class Keyboard(val one_time: Boolean = false,
			   val buttons: MutableList<MutableList<Button>> = mutableListOf(),
			   val inline: Boolean = false) {
	override fun toString(): String {
		val gsonInstance = Gson()
		return gsonInstance.toJson(this)
	}
}
