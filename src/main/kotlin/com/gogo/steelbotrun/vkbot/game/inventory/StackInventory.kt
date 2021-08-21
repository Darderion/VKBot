package com.gogo.steelbotrun.vkbot.game.inventory

import com.gogo.steelbotrun.vkbot.game.inventory.item.Item
import com.gogo.steelbotrun.vkbot.game.inventory.slot.InventoryStack
import kotlin.math.ceil
import kotlin.math.floor


class StackInventory(val size: Int, override val slots: MutableList<InventoryStack>) :
	GenericInventory<InventoryStack>() {
	private fun findItem(item: Item): MutableList<Int> {
		val indexes = mutableListOf<Int>()
		slots.forEachIndexed { index, stack -> if (stack.item.name == item.name) indexes.add(index) }
		return indexes
	}

	override fun addItem(item: Item, amount: Int): InventoryResponse {
		var amount = amount
		var numberOfStacks = ceil(amount.toDouble() / item.max.toDouble()).toInt()
		val indexes = this.findItem(item)
		val lastItem = if (indexes.isNotEmpty()) this.slots[indexes.last()] else null
		if (indexes.isEmpty() || (lastItem !== null && lastItem.count() == item.max)) {
			if (this.slots.size >= this.size) return InventoryResponse(false, "Inventory is full")
			if (this.slots.size + numberOfStacks > this.size) return InventoryResponse(
				false,
				"Inventory can't take that much"
			)
			if (numberOfStacks == 1) {
				this.slots.add(InventoryStack(item, amount))
			} else {
				for (i in 0 until numberOfStacks - 1) {
					this.slots.add(InventoryStack(item, item.max)); amount -= item.max
				}
				if (amount > 0) this.slots.add(InventoryStack(item, amount))
			}
			return InventoryResponse(true, "Items added successfully")
		} else {
			val difference = lastItem!!.item.max - lastItem.count()
			if (amount - difference < 0) {
				this.slots[indexes.last()] = InventoryStack(item, lastItem.count() + amount)
				return InventoryResponse(true, "Items added successfully")
			} else {
				amount -= lastItem.item.max - lastItem.count()
				numberOfStacks = ceil(amount.toDouble() / item.max.toDouble()).toInt()

				if (this.slots.size >= this.size) return InventoryResponse(false, "Inventory is full")
				if (this.slots.size + numberOfStacks > this.size) return InventoryResponse(
					false,
					"Inventory can't take that much"
				)

				this.slots[indexes.last()] = InventoryStack(item, item.max)

				if (numberOfStacks == 1) {
					this.slots.add(InventoryStack(item, amount))
				} else {
					for (i in 0 until numberOfStacks - 1) {
						this.slots.add(InventoryStack(item, item.max)); amount -= item.max
					}
					if (amount > 0) this.slots.add(InventoryStack(item, amount))
				}
				return InventoryResponse(true, "Items added successfully")
			}
		}
	}

	override fun removeItem(item: Item, amount: Int): InventoryResponse {
		var amount = amount
		var numberofStacksToRemove = floor(amount.toDouble() / item.max.toDouble()).toInt()
		var indexes = this.findItem(item)
		if (indexes.isEmpty()) return InventoryResponse(false, "Inventory does not contain that item")
		val lastIndex = indexes.last()
		val lastItem = this.slots[lastIndex]
		var carryOver = false
		if (numberofStacksToRemove > indexes.size || (numberofStacksToRemove >= indexes.size && lastItem.count() != item.max))
			return InventoryResponse(false, "Inventory does not contains that many items")
		val total = indexes.map { slots[it].count() }.reduce { acc, it -> acc + it }
		val remainder = total - amount
		if (amount <= lastItem.count()) {
			if (amount == lastItem.count()) this.slots.removeAt(lastIndex) else this.slots[lastIndex] =
				InventoryStack(item, lastItem.count() - amount)
			return InventoryResponse(false, "Deleted successfully")
		}
		val lastItemCount = remainder % item.max
		if (amount % item.max > lastItem.count()) carryOver = true
		if (carryOver) {
			this.slots.removeAt(lastIndex)
			this.slots[indexes[indexes.size - 2]] = InventoryStack(item, lastItemCount)
			amount -= (lastItem.count() + item.max - lastItemCount)
			numberofStacksToRemove = floor(amount.toDouble() / item.max.toDouble()).toInt()
		}
		if (numberofStacksToRemove > 0) {
			for (i in 0 until numberofStacksToRemove) {
				this.slots.removeAt(indexes.first());
				indexes.removeAt(0)
				indexes = findItem(item)
				amount -= item.max
			}
			if (indexes.isNotEmpty() && amount > 0) {
				if (amount == lastItem.count()) this.slots.removeAt(indexes.last()) else this.slots[indexes.last()] =
					InventoryStack(item, lastItem.count() - amount)
			}
		}
		return InventoryResponse(false, "Deleted successfully")
	}

	override fun addItem(inventorySlot: InventoryStack) = addItem(inventorySlot.item, inventorySlot.count())

	override fun removeItem(inventorySlot: InventoryStack) = removeItem(inventorySlot.item, inventorySlot.count())
}
