package com.gogo.steelbotrun.vkbot.event

import org.springframework.stereotype.Component

@Component
class EventBuilder {
	fun getEvent(receivedData: String): Event {
		println("Received data: "+receivedData)

		val event = Event(receivedData)
		return when(event.type) {
			"message_new" -> EventMessage(receivedData)
			"confirmation" -> EventConfirmation(receivedData)
			else -> throw Exception("Unknown event type '${event.type}'")
		}
	}
}
