package com.gogo.steelbotrun.vkbot.game.character.stats

import com.gogo.steelbotrun.vkbot.game.character.stats.Stats
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.shouldBeExactly
import io.kotest.matchers.ints.shouldBeExactly

class StatsTests : StringSpec({
	"Stats' companion object should contain stats from file" {
		Stats.length shouldBeExactly 3
	}
	"Stat A * Stat B == Stat AB, where AB[i] == A[i] * B[i]" {
		val a = Stats(listOf(1.0, 2.0, 3.0))
		val b = Stats(listOf(5.0, 7.0, 9.0))

		val stats = a * b

		stats[0] shouldBeExactly 5.0
		stats[1] shouldBeExactly 14.0
		stats[2] shouldBeExactly 27.0
	}
	"Stat A + Stat B == Stat AB, where AB[i] == A[i] + B[i]" {
		val a = Stats(listOf(1.0, 2.0, 3.0))
		val b = Stats(listOf(2.0, 3.0, 5.0))

		val stats = a + b

		stats[0] shouldBeExactly 3.0
		stats[1] shouldBeExactly 5.0
		stats[2] shouldBeExactly 8.0
	}
	"Stats' sum should be equal to sum of Stat's elements" {
		val a = Stats(listOf(1.0, 2.0, 5.0))
		a.sum shouldBeExactly 8.0
	}
})
