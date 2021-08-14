package com.gogo.steelbotrun.vkbot.utils.logger

import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class LoggerEvent(val text: String, val type: LoggerEventTypes) {
	val date: String

	init {
		// https://stackoverflow.com/questions/49862357/how-do-i-get-the-current-time-as-a-timestamp-in-kotlin
		date = DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
			.withZone(ZoneOffset.UTC)
			.format(Instant.now())
	}

	override fun toString() = "$date | $text"
}