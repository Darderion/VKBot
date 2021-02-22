package com.gogo.steelbotrun.vkbot.event

const val VKConfirmationToken = "80ae03d1"
// c0451885
// d2b2e55b
// 00011fbf
// a85287d7
// 22f9fcc0
// 80ae03d1

class EventConfirmation(receivedData: String): Event(receivedData) {
	override fun response() = VKConfirmationToken
}
