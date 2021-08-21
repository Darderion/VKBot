package com.gogo.steelbotrun.vkbot.game.inventory.item

import com.gogo.steelbotrun.vkbot.core.condition.Condition
import com.gogo.steelbotrun.vkbot.core.condition.predicate.PredicateTrue
import com.gogo.steelbotrun.vkbot.core.condition.predicate.and
import com.gogo.steelbotrun.vkbot.core.condition.predicate.toPredicate
import com.gogo.steelbotrun.vkbot.game.character.bodyparts.BodyParts
import com.gogo.steelbotrun.vkbot.game.character.stats.Stats
import com.gogo.steelbotrun.vkbot.game.inventory.item.ItemType.*

class Item(
	val id: Int,
	val name: String,
	val stats: Stats,
	val type: ItemType,
	val max: Int = 1,
	val requirements: Condition = PredicateTrue(),
	val bodyParts: BodyParts<Boolean> = BodyParts(),
	val description: String = ""
	) {
	fun canUse(vararg args: Any) = (when(type) {
			Equipment -> {
				val characterBodyParts = args.first { it is BodyParts<*> } as BodyParts<*>

				// Cast doesn't work if generic type is Any, Boolean? or other type that can contain Boolean values
				if (characterBodyParts.parts.any { it != null && it is Boolean } ||
					characterBodyParts.parts.filterNotNull().isEmpty()) {
					(characterBodyParts as BodyParts<Boolean> doesNotIntersectWith bodyParts).toPredicate()
				} else throw Error("No body parts found")
			}
			else -> PredicateTrue()
		} and requirements).interpret(*args)
}
