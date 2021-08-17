package com.gogo.steelbotrun.vkbot.game.inventory.item

import com.gogo.steelbotrun.vkbot.core.condition.Condition
import com.gogo.steelbotrun.vkbot.core.condition.toComparisonType
import com.gogo.steelbotrun.vkbot.game.battle.actions.ActionType
import com.gogo.steelbotrun.vkbot.game.battle.actions.Area
import com.gogo.steelbotrun.vkbot.game.battle.actions.SimpleAction
import com.gogo.steelbotrun.vkbot.game.character.bodyparts.BodyParts
import com.gogo.steelbotrun.vkbot.game.character.stats.Stats
import com.gogo.steelbotrun.vkbot.game.condition.ConditionStat
import java.io.File
import kotlin.Error

class ItemRepository(filePath: String = "/src/test/resources/static/test_items.txt") {
	private val items = mutableListOf<Item>()

	fun items() = items.toList()

	init {
		val itemsFile = File("${System.getProperty("user.dir")}$filePath").readLines()
		val itemsText = itemsFile.map { it.trim() }.filter { it.isNotEmpty() }.filter { it[0] != '#' } + ":"

		var itemName = ""
		val itemRequirements = mutableListOf<String>()
		var description = ""
		var itemType = ""
		var requirements: List<Condition>
		val itemStats = mutableListOf<Pair<String, Double>>()

		itemsText.forEach {
			if (it.contains(":")) {
				if (itemStats.count() != 0 || itemRequirements.count() != 0) {
					// Add item
					val item = when (itemType) {
						"Equipable"  -> Equipment(1, itemName, Stats(*itemStats.toTypedArray()), BodyParts())
						"Consumable" -> Consumables(1, itemName, Stats(*itemStats.toTypedArray()))
						else         -> Item(1, itemName, Stats(*itemStats.toTypedArray()))
					}
					items.add(item)
					// Clear item's fields
					itemName = ""
					itemStats.clear()
					itemRequirements.clear()
					description = ""
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
					} else {
						if (itemText.count() == 1) {
							// Item type
							when (itemText.first()) {
								"equipable"  -> itemType = "Equipable"
								"consumable" -> itemType = "Consumable"
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