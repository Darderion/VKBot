package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.battle.Stats
import com.gogo.steelbotrun.vkbot.battle.actions.ActionType
import com.gogo.steelbotrun.vkbot.battle.actions.Area
import com.gogo.steelbotrun.vkbot.battle.actions.MovesRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.ints.shouldBeExactly

class StatsTests : StringSpec({
	"Stats' companion object should contain stats from file" {
		Stats.names.forEach(::println)
		Stats.numberOfStats shouldBeExactly 3
	}
})
