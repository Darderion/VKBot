package com.gogo.steelbotrun.vkbot.utils.logger

class Logger {
	private val logs = mutableListOf(LoggerEvent("Log", LoggerEventTypes.logger))

	infix fun write(text: String) {
		logs.add(LoggerEvent(text, LoggerEventTypes.log))
	}

	infix fun warning(text: String) {
		logs.add(LoggerEvent(text, LoggerEventTypes.warning))
	}

	infix fun exception(text: String) {
		logs.add(LoggerEvent(text, LoggerEventTypes.exception))
	}
}