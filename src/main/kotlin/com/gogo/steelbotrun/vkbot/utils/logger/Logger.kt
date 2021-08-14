package com.gogo.steelbotrun.vkbot.utils.logger

class Logger {
	private val logs = mutableListOf(LoggerEvent("Log", LoggerEventTypes.Logger))

	infix fun write(text: String) {
		logs.add(LoggerEvent(text, LoggerEventTypes.Log))
	}

	infix fun warning(text: String) {
		logs.add(LoggerEvent(text, LoggerEventTypes.Warning))
	}

	infix fun exception(text: String) {
		logs.add(LoggerEvent(text, LoggerEventTypes.Exception))
	}
}