package com.gogo.steelbotrun.vkbot.event

import com.gogo.steelbotrun.vkbot.json.JSON

class MessageInfo(val date: String, val fromId: Int, val peerId: Int, val id: Int, val payload: JSON? = null) {
}
