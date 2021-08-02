package com.gogo.steelbotrun.vkbot.battle

import java.io.File

class Stats {
	private val values = listOf<Double>()

	companion object {
		// HashMap<String, Int>: Name -> Id
		val names = File("${System.getProperty("user.dir")}/src/main/resources/static/stats.txt").readLines()
			.asSequence()
			.map { it.trim() }
			.filter { it.isNotEmpty() && it[0] != '#' }
			.mapIndexed { index, it -> it.split(" ", "\t").filter { it.isNotEmpty() }.map { Pair(it, index) } }
			.flatten()
			.associateBy({ it.first }, { it.second })

		val numberOfStats = names.values.toSet().count()
	}
}