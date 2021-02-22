package com.gogo.steelbotrun.vkbot

import java.io.*
import java.net.URL
import java.net.URLConnection
import java.nio.file.Files
import java.util.*
import kotlin.collections.HashMap
import java.io.OutputStreamWriter

import java.io.PrintWriter
import java.io.BufferedReader
import java.io.FileInputStream





const val vkapi = "https://api.vk.com/method/"

class SDK {
	enum class RequestType {
		Get, Post
	}

	companion object {
		private fun sendMultiPartFormData(url: String, filePath: String): String {
			// https://stackoverflow.com/questions/2469451/upload-files-from-java-client-to-a-http-server

			val charset = "UTF-8"
			val param = "value"
			val binaryFile = File(filePath)
			val boundary =
				java.lang.Long.toHexString(System.currentTimeMillis()) // Just generate some unique random value.

			val connection = URL(url).openConnection()
			connection.doOutput = true
			connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=$boundary")

			/*
			val stream = connection.getInputStream()
			val streamReader = InputStreamReader(stream)
			val response = BufferedReader(streamReader)
			val output = response.readLine()
			response.close()
			return output
			 */

			val output = connection.getOutputStream()
			val writer = PrintWriter(OutputStreamWriter(output, "UTF-8"))
			var content = "--$boundary" +
					"Content-Disposition: form-data; name=\"" + filePath + "\"; filename=\"" + filePath + "\""
			/*
			try {
				writer.println("--$boundary")
				writer.println("Content-Disposition: form-data; name=\"" + filePath + "\"; filename=\"" + filePath + "\"")
				// writer.println("Content-Type: text/plain; charset=UTF-8")
				// multipart/form-data
				writer.println("Content-Type: multipart/form-data; charset=UTF-8")
				writer.println()
				var reader: BufferedReader? = null
				try {
					reader = BufferedReader(InputStreamReader(FileInputStream(filePath), "UTF-8"))
					var line: String?
					while (reader.readLine().also { line = it } != null) {
						writer.println(line)
					}
				} finally {
					reader?.close()
				}
				writer.println("--$boundary--")
			} finally {
				if (writer != null) writer.close()
			}
			 */

			val br = BufferedReader(InputStreamReader(connection.getInputStream()))
			var sb = ""
			while (br.readLine().also { sb += "\n${it}" } != null) { }

			return sb
		}

		private fun sendRequest(
			url: String,
			requestType: RequestType = RequestType.Get
		): String {
			var url = url
				.replace(" ", "%20")
				.replace("'", "%60")
				.replace("\n", "%0A")

			val urlObject = URL(url)
			val conn = urlObject.openConnection()

			if (requestType == RequestType.Post) {
				conn.doOutput = true
			}

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
			return sendRequest(url)
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
			return sendRequest(url)
		}

		fun delete(messageId: String) = sendRequest(
			"${vkapi}messages.deleteConversation?" +
					"messageIds=${messageId}&" +
					"spam=0&" +
					"delete_for_all=1&" +
					"access_token=${token}&" +
					"v=5.120"
		)

		/**
		 * Returns an object with an upload_url, album_id and group_id (for community messages) fields
		 */
		fun getMessageUploadServer(peerId: Int) = sendRequest(
			"${vkapi}photos.getMessagesUploadServer?" +
					"peer_id=${peerId}&" +
					"access_token=${token}&" +
					"v=5.120"
		)

		/**
		 * Returns an array with the uploaded photo, the returned object contains id, pid, aid, owner_id, src, src_big, src_small, created fields
		 * In case there are high-resolution photos, addresses with src_xbig and src_xxbig names will also be returned.
		 */
		fun saveMessagePhoto(photoURL: String, server: Int, hashCode: String) = sendRequest(
			"${vkapi}photos.saveMessagesPhoto?" +
					"photo=${photoURL}&" +
					"server=${server}&" +
					"hash=${hashCode}&" +
					"access_token=${token}&" +
					"v=5.120"
		)

		fun uploadPhoto(serverURL: String, filePath: String): String {
			return sendMultiPartFormData(
				serverURL,
				filePath)
		}
	}
}