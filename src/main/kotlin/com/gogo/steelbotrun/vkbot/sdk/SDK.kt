package com.gogo.steelbotrun.vkbot.sdk

import com.gogo.steelbotrun.vkbot.json.JSONGoogle
import com.gogo.steelbotrun.vkbot.json.JSONJackson
import com.gogo.steelbotrun.vkbot.sdk.Attachment.*
import com.gogo.steelbotrun.vkbot.sdk.HttpClient.Companion.sendMultiPartFormRequest
import com.gogo.steelbotrun.vkbot.sdk.HttpClient.Companion.sendRequest
import java.io.*
import java.util.*
import kotlin.collections.HashMap

const val vkapi = "https://api.vk.com/method/"
val token = File("${System.getProperty("user.dir")}/BotInfo.txt").readLines().first()
const val apiVersion = "5.120"

class SDK {
	enum class RequestType {
		Get, Post
	}

	companion object {
		private fun uploadFile(path: String, userId: Int): Attachment {
			val getUploadServer = sendRequest(
				"${vkapi}docs.getMessagesUploadServer?" +
						"peer_id=${userId}" +
						"&type=doc" +
						"&access_token=${token}" +
						"&v=${apiVersion}"
			)
			val responseBody = JSONJackson(getUploadServer).getJSON("response")
			val uploadUrl = responseBody.get("upload_url")

			val fileResponse = sendMultiPartFormRequest(uploadUrl, path)

			val fileJSON = JSONGoogle(fileResponse).get("file")
			val fileData = sendRequest(
				"${vkapi}docs.save?" +
						"&file=${fileJSON}" +
						"&title=fighting.gif" +
						"&access_token=${token}" +
						"&v=${apiVersion}")

			val jsonFileData = JSONGoogle(fileData).getJSON("response/doc")
			val id = jsonFileData.getInt("id")
			val ownerId = jsonFileData.getInt("owner_id")

			return Attachment(AttachmentType.doc, ownerId, id)
		}

		/**
		 * Sends a message
		 * @param message Text that needs to be sent to the user
		 * @param userId User ID
		 * @param attachments List of file paths to attachments
		 * @param keyboard Keyboard
		 * @return Response
		 */
		fun send(message: String,
				 userId: Int,
				 attachments: List<String> = listOf(),
				 keyboard: String? = null
		): String {
			val messageToken = Date().time.toInt()
			val attachmentsURL = attachments.map {
				uploadFile(it, userId)
			}.joinToString("&") {
				"attachment=${it}"
			} + if (attachments.isNotEmpty()) "&" else ""
			val keyboardUrl = if (keyboard != null) "keyboard=${keyboard}&" else ""

			val url = "${vkapi}messages.send?" +
					"message=${message}&" +
					"user_id=${userId}&" +
					"access_token=${token}&" +
					"random_id=${messageToken}&" +
					"v=${apiVersion}&" +
					attachmentsURL +
					keyboardUrl
			return sendRequest(url)
		}

		/**
		 * Get info about user using shortcut profile name or user ID
		 * @param profileIdentifier User ID or User shortcut profile name
		 * @return profile info
		 */
		fun getUserInfo(profileIdentifier: String): String {
			val url = "${vkapi}users.get?" +
					"user_ids=${profileIdentifier}&" +
					"access_token=${token}&" +
					"v=${apiVersion}"
			return sendRequest(url)
		}

		/**
		 * Sends a message
		 * @param message Text that needs to be sent to the users
		 * @param users List of user IDs
		 * @param attachments List of file paths to attachments
		 * @return HashMap: USER_ID -> response
		 */
		fun send(message: String, users: List<Int>, attachments: List<String> = listOf()): HashMap<Int, String> {
			val map: HashMap<Int, String> = hashMapOf()
			users.forEach {
				map[it] = send(message, it, attachments)
			}
			return map
		}

		fun deleteMessages(userId: String): String {
			val url = "${vkapi}messages.deleteConversation?" +
					"user_id=${userId}&" +
					"access_token=${token}&" +
					"v=${apiVersion}"
			return sendRequest(url)
		}

		fun delete(messageId: String) = sendRequest(
			"${vkapi}messages.deleteConversation?" +
					"messageIds=${messageId}&" +
					"spam=0&" +
					"delete_for_all=1&" +
					"access_token=${token}&" +
					"v=${apiVersion}"
		)

		/**
		 * Returns an object with an upload_url, album_id and group_id (for community messages) fields
		 */
		fun getMessageUploadServer(peerId: Int) = sendRequest(
			"${vkapi}photos.getMessagesUploadServer?" +
					"peer_id=${peerId}&" +
					"access_token=${token}&" +
					"v=${apiVersion}"
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
					"v=${apiVersion}"
		)
	}
}