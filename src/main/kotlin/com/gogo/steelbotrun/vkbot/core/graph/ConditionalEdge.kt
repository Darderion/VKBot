package com.gogo.steelbotrun.vkbot.core.graph

import com.gogo.steelbotrun.vkbot.core.condition.Condition
import com.gogo.steelbotrun.vkbot.core.graph.GraphEdge

class ConditionalEdge(fromNodeId: Int, toNodeId: Int, var text: String = "", var condition: Condition): GraphEdge(fromNodeId, toNodeId) {
}
