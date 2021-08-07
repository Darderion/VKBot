package com.gogo.steelbotrun.vkbot.graphs.text

import com.gogo.steelbotrun.vkbot.graphs.Graph
import java.lang.Error

class TextGraph: Graph<String>() {
	fun findNodeContaining(text: String): Int {
		val text = text.toLowerCase()
		val nodesList = nodes.filter { it.value.toLowerCase().contains(text) }
		if (nodesList.count() != 1) throw Error("${nodesList.count()} nodes contain '$text'")
		return nodesList.first().id
	}
}
