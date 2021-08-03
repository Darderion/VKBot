package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.character.stats.Stats
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.doubles.shouldBeExactly
import io.kotest.matchers.ints.shouldBeExactly
import java.lang.Error

class StatsTests : StringSpec({
	"Stats should not initialize StatsRepository more than once" {
		Stats.setup("/src/test/resources/static/test_stats.txt")
		var exceptionCaught = false
		try {
			Stats.setup("/src/test/resources/static/test_stats.txt")
		} catch (e: Error) {
			exceptionCaught = true
		}
		exceptionCaught shouldBeEqualComparingTo true
	}
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
})
