package com.gogo.steelbotrun.vkbot.events

import com.gogo.steelbotrun.vkbot.Server

const val VKConfirmationToken = "a85287d7"
// c0451885
// d2b2e55b
// 00011fbf
// a85287d7

class EventConfirmation(receivedData: String): Event(receivedData) {
	override fun response() = VKConfirmationToken
}
