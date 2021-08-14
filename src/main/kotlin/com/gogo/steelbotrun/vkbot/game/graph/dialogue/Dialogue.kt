package com.gogo.steelbotrun.vkbot.game.graph.dialogue

import com.gogo.steelbotrun.vkbot.core.condition.Condition
import com.gogo.steelbotrun.vkbot.core.condition.predicate.PredicateTrue
import com.gogo.steelbotrun.vkbot.core.graph.ConditionalEdge
import com.gogo.steelbotrun.vkbot.core.graph.Graph

class Dialogue : Graph<String, DialogueNode, ConditionalEdge>() {
	override fun addNode(value: String) {
		val node = DialogueNode(value)
		addNode(node)
	}

	override fun getNewNode(value: String) = DialogueNode(value)

	override fun getNewEdge(fromNodeId: Int, toNodeId: Int) =
		ConditionalEdge(fromNodeId, toNodeId, "", PredicateTrue())

	fun addCondition(fromNodeId: Int, toNodeId: Int, condition: Condition) {
		edges[fromNodeId]!!.first { it.toNodeId == toNodeId }.condition = condition
	}
}
