package com.gogo.steelbotrun.vkbot.game.map.dialogue

import com.gogo.steelbotrun.vkbot.core.graph.ConditionalEdge
import com.gogo.steelbotrun.vkbot.core.graph.GraphPosition

class DialoguePosition(override val graph: Dialogue, override var current: DialogueNode = graph.nodes.first()):
	GraphPosition<String, DialogueNode, ConditionalEdge>(graph, graph.nodes.first()) {

	override val paths: List<DialoguePosition>
		get() = graph.edges[current.id]!!
			.filter { it.condition.interpret() }
			.map { edge -> DialoguePosition(graph, graph.nodes.first { it.id == edge.toNodeId }) }
}
