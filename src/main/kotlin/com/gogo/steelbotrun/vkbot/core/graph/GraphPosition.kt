package com.gogo.steelbotrun.vkbot.core.graph

open class GraphPosition<T, V: GraphNode<T>, E: GraphEdge>(open val graph: Graph<T, V, E>, open var current: V):
	Position() {
	open val paths: List<GraphPosition<T, V, E>>
	get() = graph.edges[current.id]!!.map { edge ->
		GraphPosition(graph, graph.nodes.first { it.id == edge.toNodeId }) }

	fun move(node: V) {
		if (paths.contains(node))
			current = node
	}

	override fun mapName() = graph.toString()
}
