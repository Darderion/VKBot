package com.gogo.steelbotrun.vkbot.game.inventory

import com.gogo.steelbotrun.vkbot.game.inventory.item.Item
import com.gogo.steelbotrun.vkbot.game.inventory.slot.InventorySlot

interface Inventory<T: InventorySlot> {
	val items: Collection<T>
	fun addItem(inventorySlot: T): InventoryResponse
	fun addItem(item: Item, amount: Int): InventoryResponse
	fun removeItem(inventorySlot: T): InventoryResponse
	fun removeItem(item: Item, amount: Int): InventoryResponse
}
