package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.battle.actions.ActionType
import com.gogo.steelbotrun.vkbot.battle.actions.Area
import com.gogo.steelbotrun.vkbot.battle.actions.MovesRepository
import com.gogo.steelbotrun.vkbot.character.stats.Stats
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.doubles.shouldBeExactly
import io.kotest.matchers.ints.shouldBeExactly

class FightingTests : StringSpec({
	"Moves Repository should contain moves from file" {
		val moves = MovesRepository("/src/test/resources/static/test_moves.txt").moves()

		moves.first().name shouldBeEqualComparingTo "Kick"
		moves[1].name shouldBeEqualComparingTo "Side Kick"

		moves.first().getEffect(ActionType.Attack, Area.Body) shouldBeExactly 15.0
		moves[1].getEffect(ActionType.Attack, Area.Body) shouldBeExactly 0.0
		moves[1].getEffect(ActionType.Attack, Area.Head, Stats("Strength" to 2.0, "Agility" to 1.0)) shouldBeExactly 18.5

		moves.first().description shouldBeEqualComparingTo "Kick from Karate movies"
		moves[1].description shouldBeEqualComparingTo "Cool move from fighting on ps3"
	}
})
