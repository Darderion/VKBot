package com.gogo.steelbotrun.vkbot.inventory.items

interface Inventory {
	abstract val items: Any
	fun addItem() {}
	fun removeItem() {}
}