package com.gogo.steelbotrun.vkbot.battle.fighters

import com.gogo.steelbotrun.vkbot.battle.actions.Move
import com.gogo.steelbotrun.vkbot.battle.actions.ActionType.*
import com.gogo.steelbotrun.vkbot.battle.actions.Area.*
import com.gogo.steelbotrun.vkbot.battle.actions.SimpleAction

class Player(name: String, val id: String): Fighter(name,
	listOf(Head, Body, Legs),
	listOf(
		Move("Nitoryu: Dragon's Fangs", listOf(
			SimpleAction(Dodge, Head, 100),
			SimpleAction(Attack, Body, 10),
			SimpleAction(Penalty, Legs, 5)
		)),
		Move("Block", listOf(
			SimpleAction(Block, Body, 20)
		))
	)
)
