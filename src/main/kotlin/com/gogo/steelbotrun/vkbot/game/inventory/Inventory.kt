package com.gogo.steelbotrun.vkbot.game.inventory

import com.gogo.steelbotrun.vkbot.game.inventory.item.Item

interface Inventory {
	fun addItem(item: Item, amount: Int): InventoryResponse
	fun removeItem(item: Item, amount: Int): InventoryResponse
}
