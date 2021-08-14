package com.gogo.steelbotrun.vkbot.game.graph.dialogue

import com.gogo.steelbotrun.vkbot.core.graph.ConditionalEdge
import com.gogo.steelbotrun.vkbot.core.graph.GraphNode
import com.gogo.steelbotrun.vkbot.game.graph.Position

class DialoguePosition(override val graph: Dialogue, override var current: DialogueNode = graph.nodes.first()):
	Position<String, DialogueNode, ConditionalEdge>(graph, graph.nodes.first()) {

	override val paths: List<DialogueNode>
		get() = graph.edges[current.id]!!
			.filter { it.condition.resolve() }
			.map { edge -> graph.nodes.first { it.id == edge.toNodeId } }
}
