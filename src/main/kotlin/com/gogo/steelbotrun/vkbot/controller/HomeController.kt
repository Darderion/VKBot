package com.gogo.steelbotrun.vkbot.controller

import com.gogo.steelbotrun.vkbot.sdk.SDK
import com.gogo.steelbotrun.vkbot.Server
import com.gogo.steelbotrun.vkbot.event.EventBuilder
import com.gogo.steelbotrun.vkbot.event.EventMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.io.File

@Controller
class HomeController {

    @Autowired
    lateinit var server: Server

    @GetMapping("/callback")
    fun index(): ResponseEntity<String> {
        return ResponseEntity("Some text", HttpStatus.OK)
    }

    @PostMapping("/callback")
    fun postRequest(@RequestBody request: String): ResponseEntity<String> {
        val eventBuilder = EventBuilder()

        // Constructs an appropriate event depending on the request's body
        val message = eventBuilder.getEvent(request)
        val response = message.response()

        if (response != null) {
            return ResponseEntity(response, HttpStatus.OK)
        }

        if (message is EventMessage) {
            SDK.send(
                message.text,
                message.info.fromId,
                listOf("images/fighting1.gif")
            )
        }

        return ResponseEntity("OK", HttpStatus.OK)
    }
}
