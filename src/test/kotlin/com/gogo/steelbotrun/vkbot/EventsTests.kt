package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.event.EventFactory
import com.gogo.steelbotrun.vkbot.sdk.SDK
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.mockk.every
import io.mockk.mockk

class EventsTests : StringSpec({
	"Duel request message should add request to server" {
		val sdk = mockk<SDK>()
		every { sdk.send(any(), any(), any(), any()) } returns "OK"
		val server = Server(sdk = sdk)
		server.fights.count() shouldBeEqualComparingTo 0
		server.requests.count() shouldBeEqualComparingTo 0
		val eventBuilder = EventFactory()
		val messageEvent = eventBuilder.getEvent(userMessage("duel 2"))
		server.process(messageEvent)

		server.fights.count() shouldBeEqualComparingTo 0
		server.requests.count() shouldBeEqualComparingTo 1
	}
}) {
	companion object {
		fun userMessage(text: String) = """
			{
				"type":"message_new",
				"object":
				{
					"message":
					{
						"date":1627363371,
						"from_id":1,
						"id":1,
						"out":0,
						"peer_id":1,
						"text":"$text",
						"conversation_message_id":1,
						"fwd_messages":[],
						"important":false,
						"random_id":0,
						"attachments":[],
						"is_hidden":false
					},
					"client_info":
					{
						"button_actions":
						[
							"text",
							"vkpay",
							"open_app",
							"location",
							"open_link",
							"callback",
							"intent_subscribe",
							"intent_unsubscribe"
						],
						"keyboard":true,
						"inline_keyboard":true,
						"carousel":true,
						"lang_id":3
					}
				},
				"group_id":1,
				"event_id":"1"
			}
		""".trimIndent()
	}
}
