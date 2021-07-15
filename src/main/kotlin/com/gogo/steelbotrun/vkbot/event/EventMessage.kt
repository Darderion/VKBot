package com.gogo.steelbotrun.vkbot.event

class EventMessage(receivedData: String): Event(receivedData) {
	var text: String
	val info: MessageInfo

	init {
		val json = json.getJSON("object/message")
		val id = json.getInt("id")
		val fromId = json.getInt("from_id")
		val peerId = json.getInt("peer_id")
		val date = json.get("date")
		text = json.get("text")
		info = MessageInfo(date, fromId, peerId, id)
	}
}
