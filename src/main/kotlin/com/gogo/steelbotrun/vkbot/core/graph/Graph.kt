package com.gogo.steelbotrun.vkbot.core.graph

import java.lang.Error

abstract class Graph<T, V: GraphNode<T>, E: GraphEdge>: Identifiable() {
	val nodes: HashSet<V> = hashSetOf()
	val edges: HashMap<Int, HashSet<E>> = hashMapOf()

	/**
	 * Adds a node with value VALUE
	 * @param value Value of a node
	 */
	open fun addNode(value: T) {
		val graphNode = getNewNode(value)
		addNode(graphNode)
	}

	fun addNode(node: V) {
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

	open fun addEdge(fromNodeId: Int, toNodeId: Int) {
		edges[fromNodeId]!!.add(getNewEdge(fromNodeId, toNodeId))
	}

	protected fun addEdge(graphEdge: E) {
		edges[graphEdge.fromNodeId]!!.add(graphEdge)
	}

	abstract fun getNewNode(value: T): V
	abstract fun getNewEdge(fromNodeId: Int, toNodeId: Int): E
}
