package com.gogo.steelbotrun.vkbot.game.inventory.item

import com.gogo.steelbotrun.vkbot.core.condition.Condition
import com.gogo.steelbotrun.vkbot.core.condition.predicate.PredicateAnd
import com.gogo.steelbotrun.vkbot.core.condition.toComparisonType
import com.gogo.steelbotrun.vkbot.game.character.bodyparts.BodyParts
import com.gogo.steelbotrun.vkbot.game.character.stats.Stats
import com.gogo.steelbotrun.vkbot.game.condition.ConditionStat
import com.gogo.steelbotrun.vkbot.game.inventory.item.ItemType.*
import java.io.File
import kotlin.Error

class ItemRepository(filePath: String = "/src/test/resources/static/test_items.txt") {
	private val items = mutableListOf<Item>()

	fun items() = items.toList()

	init {
		val itemsFile = File("${System.getProperty("user.dir")}$filePath").readLines()
		val itemsText = itemsFile.map { it.trim() }.filter { it.isNotEmpty() }.filter { it[0] != '#' } + ":"

		var itemName: String? = null
		var description = ""
		var itemType: ItemType? = null
		val requirements: MutableList<Condition> = mutableListOf()
		val itemStats = mutableListOf<Pair<String, Double>>()

		itemsText.forEach {
			if (it.contains(":")) {
				if (itemName != null) {
					// Add item
					val item = Item(1, itemName!!, Stats(*itemStats.toTypedArray()),
						itemType?: Quest, requirements = PredicateAnd(*requirements.toTypedArray()),
						description = description)
					items.add(item)
					// Clear item's fields
					itemName = null
					itemStats.clear()
					itemType = null
					description = ""
					requirements.clear()
				}
				itemName = it.replace(":", "")
			} else {
				if (it.contains("\"")) {
					// Description line
					if (it.length <= 2 || it[0] != '\"' || it[it.length - 1] != '\"') {
						throw Error("Description of an item is not written as \"description\"")
					}
					description = it.replace("\"", "")
				} else {
					val itemText = it.toLowerCase()
						.replace(",", "").split(" ").filter { it.isNotEmpty() }
					if (it.toLowerCase().contains("requires")) {
						// Requirements line
						val itemText = it.toLowerCase().split(" ").filter { it.isNotEmpty() }
						// TODO: Add requirements
						val conditionStats = ConditionStat(
							itemText[1],
							itemText[2].toComparisonType(),
							itemText[3].toDouble()
						)
						println(conditionStats)
						requirements.add(conditionStats)
					} else {
						if (itemText.count() == 1) {
							// Item type
							itemType = when (itemText.first()) {
								"equipable", "equipment"  -> Equipment
								"consumable" -> Consumable
								else         -> throw Error("Item type ${itemText.first()} is neither Equipable nor Consumable")
							}
						}
						if (itemText.count() % 2 == 0) {
							// Item stats
							itemText.forEachIndexed { index, item ->
								if (index % 2 == 0) {
									itemStats.add(itemText[index] to itemText[index + 1].toDouble())
								}
							}
						}
					}
				}
			}
		}
	}
}
