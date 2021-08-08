package com.gogo.steelbotrun.vkbot.inventory

import com.gogo.steelbotrun.vkbot.inventory.items.Inventory
import com.gogo.steelbotrun.vkbot.inventory.items.Item
import kotlin.math.ceil
import kotlin.math.floor


class StackInventory(val size: Int, override val items: MutableList<Pair<Item, Int>>): Inventory {

	private fun findItem(item: Item): MutableList<Int> {
		val indexes = mutableListOf<Int>()
		items.forEachIndexed { index, pair -> if(pair.first.name == item.name) indexes.add(index) }
		return indexes
	}

	fun addItem(item: Item, amount: Int): InventoryResponse {
		var amount = amount
		var numberOfStacks = ceil(amount.toDouble() / item.maxStack.toDouble()).toInt()
		val indexes = this.findItem(item)
		val lastItem = if (indexes.isNotEmpty()) this.items[indexes.last()] else null
		if (indexes.isEmpty() || (lastItem !== null && lastItem.second == item.maxStack)) {
			if(this.items.size >= this.size) return InventoryResponse(false, "Inventory is full")
			if(this.items.size + numberOfStacks > this.size) return InventoryResponse(false, "Inventory can't take that much")
			if(numberOfStacks == 1) {
				this.items.add(Pair(item, amount))
			} else {
				for(i in 0 until numberOfStacks-1) {
					this.items.add(Pair(item, item.maxStack)); amount -= item.maxStack
				}
				if(amount > 0) this.items.add(Pair(item,amount))
			}
			return InventoryResponse(true, "Items added successfully")
		} else {
			val difference = lastItem!!.first.maxStack - lastItem.second
			if(amount - difference < 0) {
				this.items[indexes.last()] = Pair(item, lastItem.second + amount)
				return InventoryResponse(true, "Items added successfully")
			} else {
				amount -= lastItem.first.maxStack - lastItem.second
				numberOfStacks = ceil(amount.toDouble() / item.maxStack.toDouble()).toInt()

				if(this.items.size >= this.size) return InventoryResponse(false, "Inventory is full")
				if(this.items.size + numberOfStacks > this.size) return InventoryResponse(false, "Inventory can't take that much")

				this.items[indexes.last()] = Pair(item, item.maxStack)

				if(numberOfStacks == 1) {
					this.items.add(Pair(item, amount))
				} else {
					for(i in 0 until numberOfStacks-1) { this.items.add(Pair(item, item.maxStack)); amount -= item.maxStack }
					if(amount > 0) this.items.add(Pair(item,amount))
				}
				return InventoryResponse(true, "Items added successfully")
			}
		}
	}

	fun removeItem(item: Item, amount: Int): InventoryResponse {
		var amount = amount
		var numberofStacksToRemove = floor(amount.toDouble() / item.maxStack.toDouble()).toInt()
		var indexes = this.findItem(item)
		if(indexes.isEmpty()) return InventoryResponse(false, "Inventory does not contain that item")
		val lastIndex = indexes.last()
		val lastItem = this.items[lastIndex]
		var carryOver = false
		if (numberofStacksToRemove > indexes.size || (numberofStacksToRemove >= indexes.size && lastItem.second != item.maxStack))
			return InventoryResponse(false, "Inventory does not contains that many items")
		val total = indexes.map {items[it].second}.reduce {acc, it -> acc + it}
		val remainder = total - amount
		if(amount <= lastItem.second) {
			if(amount == lastItem.second) this.items.removeAt(lastIndex) else this.items[lastIndex] = Pair(item, lastItem.second - amount)
			return InventoryResponse(false, "Deleted successfully")
		}
		val lastItemCount = remainder % item.maxStack
		if(amount % item.maxStack > lastItem.second) carryOver = true
		if(carryOver) {
			this.items.removeAt(lastIndex)
			this.items[indexes[indexes.size-2]] = Pair(item, lastItemCount)
			amount -= (lastItem.second + item.maxStack - lastItemCount)
			numberofStacksToRemove = floor(amount.toDouble() / item.maxStack.toDouble()).toInt()
		}
		if(numberofStacksToRemove > 0) {
			for(i in 0 until numberofStacksToRemove) {
				this.items.removeAt(indexes.first());
				indexes.removeAt(0)
				indexes = findItem(item)
				amount -= item.maxStack
			}
			if(indexes.isNotEmpty() && amount > 0) {
				if(amount == lastItem.second) this.items.removeAt(indexes.last()) else this.items[indexes.last()] = Pair(item, lastItem.second - amount)
			}
		}
		return InventoryResponse(false, "Deleted successfully")
	}
}
