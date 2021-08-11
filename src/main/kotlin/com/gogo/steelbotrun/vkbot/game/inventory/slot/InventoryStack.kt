package com.gogo.steelbotrun.vkbot.game.inventory.slot

import com.gogo.steelbotrun.vkbot.game.inventory.item.Item

class InventoryStack(item: Item, private var count: Int = 1): InventorySlot(item) {
	fun count() = count
	private val max: Int
		get() = item.max

	fun add(numberOfItems: Int): Int {
		if (numberOfItems < 0) throw Error("Number of items < 0")
		count += numberOfItems
		return if (count > max) {
			val items = count - max
			count = max
			items
		} else { 0 }
	}
}
