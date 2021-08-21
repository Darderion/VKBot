package com.gogo.steelbotrun.vkbot.game.inventory

import com.gogo.steelbotrun.vkbot.game.account.Account
import com.gogo.steelbotrun.vkbot.game.character.stats.Stats
import com.gogo.steelbotrun.vkbot.game.inventory.item.Item
import com.gogo.steelbotrun.vkbot.game.inventory.item.ItemType
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeExactly

class InventoryTests: StringSpec ({
	"Inventory should add correct number of items" {
		val testItem =  Item(1, "test Item", Stats(), ItemType.Equipment, 20)
		val testAccount = Account(StackInventory(5, mutableListOf()))
		testAccount.inventory.addItem(testItem, 20)
		testAccount.inventory.items.size shouldBeExactly 1
		testAccount.inventory.addItem(testItem, 1)
		testAccount.inventory.items.size shouldBeExactly 2
		testAccount.inventory.addItem(testItem, 18)
		testAccount.inventory.items.size shouldBeExactly 2
		testAccount.inventory.addItem(testItem, 3)
		testAccount.inventory.items.size shouldBeExactly 3
		testAccount.inventory.items.filter {it.item.name == "test Item"}
			.map { it.count() }.reduce {acc,it -> acc + it} shouldBeExactly 42
	}
	"Inventory shouldn't add items if number of items is bigger than inventory's size" {
		val testItem =  Item(1, "test Item", Stats(), ItemType.Equipment, 20)
		val testAccount = Account(StackInventory(5, mutableListOf()))
		testAccount.inventory.addItem(testItem, 99999999)
		testAccount.inventory.items.size shouldBeExactly 0
		testAccount.inventory.addItem(testItem, 100)
		testAccount.inventory.items.size shouldBeExactly 5
		testAccount.inventory.addItem(testItem, 1)
		testAccount.inventory.items.size shouldBeExactly 5
	}
	"Inventory should remove items" {
		val testItem =  Item(1, "testItem", Stats(), ItemType.Equipment, 20)
		val testItem2 =  Item(2, "testItem2", Stats(), ItemType.Equipment, 1)
		val testAccount = Account(StackInventory(5, mutableListOf()))
		testAccount.inventory.addItem(testItem, 50)
		testAccount.inventory.removeItem(testItem,25)
		testAccount.inventory.addItem(testItem2, 3)
		testAccount.inventory.items.filter { it.item.name == "testItem" }
			.map { it.count() }.reduce { acc,it -> acc + it } shouldBeExactly 25
		testAccount.inventory.removeItem(testItem2, 1)
		testAccount.inventory.items.size shouldBeExactly 4
	}
})
