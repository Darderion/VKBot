package com.gogo.steelbotrun.vkbot.bot.event

import com.gogo.steelbotrun.vkbot.utils.json.JSON

class MessageInfo(val date: String, val fromId: Int, val peerId: Int, val id: Int, val payload: JSON? = null) {
}
