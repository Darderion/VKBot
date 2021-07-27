package com.gogo.steelbotrun.vkbot.event

import java.io.File

var VKConfirmationToken = File("${System.getProperty("user.dir")}/BotInfo.txt").readLines()[2]

class EventConfirmation(receivedData: String): Event(receivedData) {
	override fun response() = VKConfirmationToken
}
