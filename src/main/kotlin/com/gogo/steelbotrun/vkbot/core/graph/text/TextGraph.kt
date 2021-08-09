package com.gogo.steelbotrun.vkbot.core.graph.text

import com.gogo.steelbotrun.vkbot.core.condition.Condition
import com.gogo.steelbotrun.vkbot.core.condition.predicate.PredicateTrue
import com.gogo.steelbotrun.vkbot.core.graph.ConditionalEdge
import com.gogo.steelbotrun.vkbot.core.graph.Graph
import com.gogo.steelbotrun.vkbot.core.graph.GraphEdge
import java.lang.Error

open class TextGraph: Graph<String>() {
	fun findNodeContaining(text: String): Int {
		val text = text.toLowerCase()
		val nodesList = nodes.filter { it.value.toLowerCase().contains(text) }
		if (nodesList.count() != 1) throw Error("${nodesList.count()} nodes contain '$text'")
		return nodesList.first().id
	}

	override fun addEdge(fromNodeId: Int, toNodeId: Int) {
		addEdge(fromNodeId, toNodeId, condition = PredicateTrue())
	}

	fun addEdge(fromNodeId: Int, toNodeId: Int, text: String = "", condition: Condition = PredicateTrue()) {
		edges[fromNodeId]!!.add(ConditionalEdge(fromNodeId, toNodeId, condition = PredicateTrue()))
	}
}
