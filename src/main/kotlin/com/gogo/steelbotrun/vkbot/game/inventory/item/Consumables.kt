package com.gogo.steelbotrun.vkbot.game.inventory.item

import com.gogo.steelbotrun.vkbot.game.character.bodyparts.BodyParts
import com.gogo.steelbotrun.vkbot.game.character.stats.Stats

class Consumables(
	id: Int,
	name: String,
	stats: Stats
): Item(id, name, stats), Consumable {
	override fun canUse(vararg args: Any) = requirements.interpret(args)
}
