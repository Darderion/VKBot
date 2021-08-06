package com.gogo.steelbotrun.vkbot.controller

import com.gogo.steelbotrun.vkbot.Server
import com.gogo.steelbotrun.vkbot.event.EventFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class HomeController {
	@Autowired
	lateinit var server: Server

	@GetMapping("/callback")
	fun index(): ResponseEntity<String> {
		println("Get request")
		return ResponseEntity("Some text", HttpStatus.OK)
	}

	@PostMapping("/callback")
	fun postRequest(@RequestBody request: String): ResponseEntity<String> {
		println("Request: $request")

		val eventBuilder = EventFactory()
		// Constructs an appropriate event depending on the request's body
		val message = eventBuilder.getEvent(request)
		val response = message.response()

		server.process(message)

		if (response != null) {
			return ResponseEntity(response, HttpStatus.OK)
		}

//		if (message is EventMessage) {
//            SDK.send(
//                message.text,
//                message.info.fromId,
//				keyboard = Keyboard(false,
//						mutableListOf(
//								mutableListOf(
//										Button(Action(label = "принять"), "positive"),
//										Button(Action(label = "отклонить"), "negative"))
//						),
//						true).toString()
//            )
//        }

		return ResponseEntity("OK", HttpStatus.OK)
	}
}
