package com.gogo.steelbotrun.vkbot.graphs

import java.lang.Error

open class Graph<T>: Identifiable() {
	val nodes: HashSet<GraphNode<T>> = hashSetOf()
	val edges: HashMap<Int, HashSet<Int>> = hashMapOf()

	/**
	 * Adds a node with value VALUE
	 * @param value Value of a node
	 */
	fun addNode(value: T) {
		val graphNode = GraphNode(value)
		addNode(graphNode)
	}

	private fun addNode(node: GraphNode<T>) {
		nodes.add(node)
		edges[node.id] = HashSet()
	}

	/**
	 * Finds ID of a node with value VALUE
	 * @param value Value of a node
	 * @return ID of a node
	 */
	fun findNode(value: T): Int {
		val nodesList = nodes.filter { it.value == value }
		if (nodesList.count() != 1) throw Error("${nodesList.count()} nodes with value = '$value'")
		return nodesList.first().id
	}

	/**
	 * Finds value of a node with id ID
	 * @param id ID of a node
	 * @return Value of a node
	 */
	fun find(id: Int) = nodes.first { it.id == id }.value

	fun get(id: Int) = find(id)

	fun addEdge(fromNodeId: Int, toNodeId: Int) {
		edges[fromNodeId]!!.add(toNodeId)
	}
}