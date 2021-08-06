package com.gogo.steelbotrun.vkbot.event

import org.springframework.stereotype.Component

@Component
class EventFactory {
	fun getEvent(receivedData: String): Event {
		val event = Event(receivedData)
		return when(event.type) {
			"message_new" -> EventMessage(receivedData)
			"confirmation" -> EventConfirmation(receivedData)
			else -> throw Exception("Unknown event type '${event.type}'")
		}
	}
}
