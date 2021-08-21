package com.gogo.steelbotrun.vkbot.game.inventory

import com.gogo.steelbotrun.vkbot.game.account.Account
import com.gogo.steelbotrun.vkbot.game.character.stats.Stats
import com.gogo.steelbotrun.vkbot.game.inventory.item.Item
import com.gogo.steelbotrun.vkbot.game.inventory.item.ItemType
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeExactly

class StackInventoryTests : StringSpec({
	"StackInventory should add correct number of items" {
		val testItem = Item(1, "test Item", Stats(), ItemType.Equipment, 20)
		val testAccount = Account(StackInventory(5, mutableListOf()))

		val inventory = testAccount.inventory as StackInventory
		inventory.addItem(testItem, 20)
		inventory.slots.size shouldBeExactly 1
		inventory.addItem(testItem, 1)
		inventory.slots.size shouldBeExactly 2
		inventory.addItem(testItem, 18)
		inventory.slots.size shouldBeExactly 2
		inventory.addItem(testItem, 3)
		inventory.slots.size shouldBeExactly 3
		inventory.slots.filter { it.item.name == "test Item" }
			.map { it.count() }.reduce { acc, it -> acc + it } shouldBeExactly 42
	}
	"StackInventory shouldn't add items if number of items is bigger than inventory's size" {
		val testItem = Item(1, "test Item", Stats(), ItemType.Equipment, 20)
		val testAccount = Account(StackInventory(5, mutableListOf()))

		val inventory = testAccount.inventory as StackInventory
		inventory.addItem(testItem, 99999999)
		inventory.slots.size shouldBeExactly 0
		inventory.addItem(testItem, 100)
		inventory.slots.size shouldBeExactly 5
		inventory.addItem(testItem, 1)
		inventory.slots.size shouldBeExactly 5
	}
	"StackInventory should remove items" {
		val testItem = Item(1, "testItem", Stats(), ItemType.Equipment, 20)
		val testItem2 = Item(2, "testItem2", Stats(), ItemType.Equipment, 1)
		val testAccount = Account(StackInventory(5, mutableListOf()))

		val inventory = testAccount.inventory as StackInventory
		inventory.addItem(testItem, 50)
		inventory.removeItem(testItem, 25)
		inventory.addItem(testItem2, 3)
		inventory.slots.filter { it.item.name == "testItem" }
			.map { it.count() }.reduce { acc, it -> acc + it } shouldBeExactly 25
		inventory.removeItem(testItem2, 1)
		inventory.slots.size shouldBeExactly 4
	}
})
