package com.gogo.steelbotrun.vkbot.game.map

import com.gogo.steelbotrun.vkbot.core.graph.Position

class Location {
	private val positions: ArrayDeque<Position> = ArrayDeque()

	fun getCurrentPosition() = positions.first()
}
