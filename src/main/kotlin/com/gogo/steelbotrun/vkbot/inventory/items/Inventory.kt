package com.gogo.steelbotrun.vkbot.inventory.items

import com.gogo.steelbotrun.vkbot.inventory.InventoryResponse
import com.gogo.steelbotrun.vkbot.inventory.InventorySlot

interface Inventory<T: InventorySlot> {
	val items: Collection<T>
	fun addItem(inventorySlot: T): InventoryResponse
	fun addItem(item: Item, amount: Int): InventoryResponse
	fun removeItem(inventorySlot: T): InventoryResponse
	fun removeItem(item: Item, amount: Int): InventoryResponse
}
