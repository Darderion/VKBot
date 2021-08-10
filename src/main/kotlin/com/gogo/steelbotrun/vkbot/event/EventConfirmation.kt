package com.gogo.steelbotrun.vkbot.event

import com.gogo.steelbotrun.vkbot.bot.event.Event

const val VKConfirmationToken = "7bc28c72"
// 1e82f06d
// c0451885
// d2b2e55b
// 00011fbf
// a85287d7
// 22f9fcc0
// 80ae03d1
// 1e82f06d

class EventConfirmation(receivedData: String): Event(receivedData) {
	override fun response() = VKConfirmationToken
}
