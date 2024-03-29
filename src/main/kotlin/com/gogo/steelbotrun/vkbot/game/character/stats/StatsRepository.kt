package com.gogo.steelbotrun.vkbot.game.character.stats

import java.io.File

class StatsRepository(filePath: String = "/src/test/resources/static/test_stats.txt") {

	private val stats: Map<String, Int> = File("${System.getProperty("user.dir")}$filePath").readLines()
		.asSequence()
		.map { it.trim().toLowerCase() }
		.filter { it.isNotEmpty() && it[0] != '#' }
		.mapIndexed { index, it -> it.split(" ", "\t").filter { it.isNotEmpty() }.map { Pair(it, index) } }
		.flatten()
		.associateBy({ it.first }, { it.second })

	fun get() = stats.toMap()
}
