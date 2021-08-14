package com.gogo.steelbotrun.vkbot.game.inventory.item

import com.gogo.steelbotrun.vkbot.game.character.stats.Stats

class Item(
	val id: Int,
	val name: String,
	val stats: Stats,
	val max: Int = 1
	)
