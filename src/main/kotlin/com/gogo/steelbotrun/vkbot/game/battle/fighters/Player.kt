package com.gogo.steelbotrun.vkbot.game.battle.fighters

import com.gogo.steelbotrun.vkbot.game.moves.Move
import com.gogo.steelbotrun.vkbot.game.battle.actions.ActionType.*
import com.gogo.steelbotrun.vkbot.game.battle.actions.Area.*
import com.gogo.steelbotrun.vkbot.game.battle.actions.SimpleAction

class Player(name: String, val id: String): Fighter(name,
	listOf(Head, Body, Legs),
	listOf(
		Move("Nitoryu: Dragon's Fangs", listOf(
			SimpleAction(Dodge, Head, 100.0),
			SimpleAction(Attack, Body, 10.0),
			SimpleAction(Penalty, Legs, 5.0)
		)),
		Move("Block", listOf(
			SimpleAction(Block, Body, 20.0)
		))
	)
)
