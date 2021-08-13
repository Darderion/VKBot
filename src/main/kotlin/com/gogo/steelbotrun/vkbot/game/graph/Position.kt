package com.gogo.steelbotrun.vkbot.game.graph

import com.gogo.steelbotrun.vkbot.core.graph.Graph
import com.gogo.steelbotrun.vkbot.core.graph.GraphEdge
import com.gogo.steelbotrun.vkbot.core.graph.GraphNode

open class Position<T, V: GraphNode<T>, E: GraphEdge>(open val graph: Graph<T, V, E>, open var current: V) {
	open val paths: List<V>
	get() = graph.edges[current.id]!!.map { edge -> graph.nodes.first { it.id == edge.toNodeId } }
}
