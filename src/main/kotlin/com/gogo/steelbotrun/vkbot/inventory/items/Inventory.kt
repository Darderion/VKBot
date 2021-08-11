package com.gogo.steelbotrun.vkbot.inventory.items

interface Inventory {
	val items: Collection<Item>
	fun addItem()
	fun removeItem()
}
