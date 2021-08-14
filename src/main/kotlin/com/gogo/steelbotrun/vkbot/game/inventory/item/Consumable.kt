package com.gogo.steelbotrun.vkbot.game.inventory.item

import com.gogo.steelbotrun.vkbot.core.condition.Condition
import com.gogo.steelbotrun.vkbot.game.character.bodyparts.BodyParts

interface Consumable {
	fun canUse(requirements: Condition) = requirements.resolve()
}