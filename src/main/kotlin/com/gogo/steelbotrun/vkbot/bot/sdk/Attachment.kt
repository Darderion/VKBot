package com.gogo.steelbotrun.vkbot.bot.sdk

class Attachment(val attachmentType: AttachmentType, val ownerId: Int, val id: Int) {
	enum class AttachmentType {
		doc, photo
	}

	override fun toString() = "${attachmentType.name}${ownerId}_${id}"
}