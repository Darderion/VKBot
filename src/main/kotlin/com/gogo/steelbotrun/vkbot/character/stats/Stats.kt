package com.gogo.steelbotrun.vkbot.character.stats

import java.io.File
import kotlin.Error

class Stats {
	private val values: List<Double>

	constructor(stats: List<Double>) {
		values = stats
		if (values.count() != length) throw Error("Incorrect number of stats")
	}

	constructor(vararg pairs: Pair<String, Double>) {
		values = MutableList(length) { 0.0 }
		pairs.forEach { values[names[it.first.toLowerCase()]!!] = it.second }
	}

	operator fun get(stat: String) = if (names.containsKey(stat.toLowerCase()))
		values[names[stat.toLowerCase()]!!] else throw Error("No stat $stat in stats")

	operator fun get(id: Int) = values[id]

	operator fun plus(a: Stats) = Stats(
		values.mapIndexed { index, d -> d + a.values[index] }
	)

	operator fun times(a: Stats) = Stats(
		values.mapIndexed { index, d -> d * a.values[index] }
	)

	val sum: Double
		get() = values.reduce { acc, d -> acc + d }

	companion object {
		private var statsRepository: StatsRepository? = null

		private var initialized = false

		// Map<String, Int>: Name -> Id
		var names: Map<String, Int> = mapOf()
			get() = if (initialized) field else { setup(); field }

		var length: Int = -1
			get() = if (initialized) field else { setup(); field }

		private fun setup() {
			statsRepository = StatsRepository()
			init()
		}

		fun setup(filePath: String) {
			statsRepository = StatsRepository(filePath)
			init()
		}

		private fun init() {
			initialized = true

			names = statsRepository!!.stats()
			length = names.values.toSet().count()
		}
	}
}