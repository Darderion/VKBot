package com.gogo.steelbotrun.vkbot.events

import com.fasterxml.jackson.databind.ObjectMapper
import com.gogo.steelbotrun.vkbot.Console
import com.gogo.steelbotrun.vkbot.Server
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class EventBuilder(server: Server) {

	val console = Console(server)

	fun getEvent(receivedData: String): Event {
		val event = Event(receivedData)
		return when(event.type) {
			"message_new" -> EventMessage(console, receivedData)
			"confirmation" -> EventConfirmation(receivedData)
			else -> throw Exception("Unknown event type '${event.type}'")
		}
	}
}
