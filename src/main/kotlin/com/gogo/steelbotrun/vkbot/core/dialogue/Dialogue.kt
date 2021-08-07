package com.gogo.steelbotrun.vkbot.core.dialogue

import com.gogo.steelbotrun.vkbot.core.graph.GraphNode
import com.gogo.steelbotrun.vkbot.core.graph.text.TextGraph

class Dialogue(): TextGraph() {
	override fun addNode(value: String) {
		val node = DialogueNode(value)
		addNode(node)
	}
}
