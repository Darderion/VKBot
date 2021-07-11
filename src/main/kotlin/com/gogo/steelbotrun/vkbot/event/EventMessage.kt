package com.gogo.steelbotrun.vkbot.event

class EventMessage(receivedData: String): Event(receivedData) {
	var date: String
	var fromId: Int
	var peerId: Int
	var text: String
	var id: Int

	init {
		val json = json.getJSON("object/message")
		id = json.getInt("id")
		fromId = json.getInt("from_id")
		peerId = json.getInt("peer_id")
		date = json.get("date")
		text = json.get("text")
	}
}
