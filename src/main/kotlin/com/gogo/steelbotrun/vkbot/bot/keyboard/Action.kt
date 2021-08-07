package com.gogo.steelbotrun.vkbot.bot.keyboard

class Action(val type: String = "text", val payload: String = "{}", var label: String) {
	init {
		label = label
			.replace(" ", "%20")
			.replace("'", "%60")
			.replace("\n", "%0A")
	}
}
