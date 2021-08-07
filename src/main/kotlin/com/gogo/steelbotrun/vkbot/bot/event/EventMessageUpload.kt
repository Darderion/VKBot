package com.gogo.steelbotrun.vkbot.bot.event

// upload_url, album_id and group_id
class EventMessageUpload(receivedData: String): Event(receivedData) {
	val uploadUrl: String
	val albumId: Int
	val groupId: Int

	init {
		val json = json.getJSON("response")
		uploadUrl = json.get("upload_url")
		albumId = json.getInt("album_id")
		groupId = json.getInt("group_id")
	}
}