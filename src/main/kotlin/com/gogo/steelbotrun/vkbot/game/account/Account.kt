package com.gogo.steelbotrun.vkbot.game.account

import com.gogo.steelbotrun.vkbot.game.inventory.Inventory
import com.gogo.steelbotrun.vkbot.game.map.Location

class Account(
	val id: String,
	val name: String,
	val inventory: Inventory,
	val location: Location = Location()
)
