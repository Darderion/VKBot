package com.gogo.steelbotrun.vkbot.game.inventory

import com.gogo.steelbotrun.vkbot.game.inventory.item.Item
import com.gogo.steelbotrun.vkbot.game.inventory.slot.InventorySlot

abstract class GenericInventory<T: InventorySlot>: Inventory {
	abstract val slots: Collection<T>
	abstract fun addItem(inventorySlot: T): InventoryResponse
	abstract fun removeItem(inventorySlot: T): InventoryResponse
}
