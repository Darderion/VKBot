package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.core.condition.predicate.PredicateFalse
import com.gogo.steelbotrun.vkbot.core.condition.predicate.PredicateTrue
import com.gogo.steelbotrun.vkbot.game.graph.dialogue.Dialogue
import com.gogo.steelbotrun.vkbot.game.graph.dialogue.DialogueNode
import com.gogo.steelbotrun.vkbot.game.graph.dialogue.DialoguePosition
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeExactly

class GraphTests : StringSpec({
	"DialoguePosition should only show paths with satisfied conditions" {
		val graph = Dialogue()
		val node1 = DialogueNode("1")
		val node2 = DialogueNode("2")
		val node3 = DialogueNode("3")
		val node4 = DialogueNode("4")

		graph.addNode(node1)
		graph.addNode(node2)
		graph.addNode(node3)
		graph.addNode(node4)

		graph.addEdge(node1.id, node2.id)
		graph.addEdge(node1.id, node3.id)
		graph.addEdge(node3.id, node4.id)

		val position = DialoguePosition(graph, node1)

		position.paths.count() shouldBeExactly 2

		graph.addCondition(node1.id, node2.id, PredicateTrue())
		graph.addCondition(node1.id, node3.id, PredicateFalse())

		position.paths.count() shouldBeExactly 1
	}
})
