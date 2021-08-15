package com.gogo.steelbotrun.vkbot.game.inventory.item

import com.gogo.steelbotrun.vkbot.core.condition.Condition
import com.gogo.steelbotrun.vkbot.game.character.bodyparts.BodyParts

interface Equipable {
	val slots: BodyParts<Boolean>

	fun canEquip(characterSlots: BodyParts<Boolean>, vararg args: Any): Boolean
}
