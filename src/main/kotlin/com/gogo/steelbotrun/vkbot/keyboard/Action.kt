package com.gogo.steelbotrun.vkbot.keyboard

class Action(val type: String = "text", val payload: String = "{}", var label: String) {
	init {
		this.label = this.label
			.replace(" ", "%20")
			.replace("'", "%60")
			.replace("\n", "%0A")
	}
}
