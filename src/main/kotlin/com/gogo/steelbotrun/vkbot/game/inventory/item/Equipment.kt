package com.gogo.steelbotrun.vkbot.game.inventory.item

import com.gogo.steelbotrun.vkbot.core.condition.Condition
import com.gogo.steelbotrun.vkbot.game.character.bodyparts.BodyParts
import com.gogo.steelbotrun.vkbot.game.character.stats.Stats

class Equipment(
	id: Int,
	name: String,
	stats: Stats,
	override val slots: BodyParts<Boolean>
): Item(id, name, stats), Equipable {
	override fun canEquip(characterSlots: BodyParts<Boolean>, vararg args: Any) =
		characterSlots doesNotIntersectWith slots && requirements.interpret(args)
}