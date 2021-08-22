package com.gogo.steelbotrun.vkbot.game.inventory

import com.gogo.steelbotrun.vkbot.game.inventory.item.Item
import com.gogo.steelbotrun.vkbot.game.inventory.response.InventoryResponse

interface Inventory {
	fun addItem(item: Item, amount: Int): InventoryResponse
	fun removeItem(item: Item, amount: Int): InventoryResponse
}
