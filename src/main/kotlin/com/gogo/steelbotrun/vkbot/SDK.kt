package com.gogo.steelbotrun.vkbot

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*
import kotlin.collections.HashMap

const val vkapi = "https://api.vk.com/method/"

class SDK {
	companion object {
		fun sendGetRequest(url: String): String {
			var url = url
				.replace(" ", "%20")
				.replace("'", "%60")
				.replace("\n", "%0A")

			val urlObject = URL(url)
			val conn = urlObject.openConnection()
			val stream = conn.getInputStream()
			val streamReader = InputStreamReader(stream)
			val response = BufferedReader(streamReader)
			val output = response.readLine()
			response.close()
			return output
		}

		fun send(message: String, userId: String): String {
			val messageToken = Date().time.toInt()
			val url = "${vkapi}messages.send?" +
					"message=${message}&" +
					"user_id=${userId}&" +
					"access_token=${token}&" +
					"random_id=${messageToken}&" +
					"v=5.120"
			return sendGetRequest(url)
		}

		fun send(message: String, users: List<String>): HashMap<String, String> {
			val map: HashMap<String, String> = hashMapOf()
			users.forEach {
				map[it] = send(message, it)
			}
			return map
		}

		fun deleteMessages(userId: String): String {
			val url = "${vkapi}messages.deleteConversation?" +
					"user_id=${userId}&" +
					"access_token=${token}&" +
					"v=5.120"
			return sendGetRequest(url)
		}

		fun delete(messageId: String) = sendGetRequest(
			"${vkapi}messages.deleteConversation?" +
					"messageIds=${messageId}&" +
					"spam=0&" +
					"delete_for_all=1&" +
					"access_token=${token}&" +
					"v=5.120"
		)
	}
}