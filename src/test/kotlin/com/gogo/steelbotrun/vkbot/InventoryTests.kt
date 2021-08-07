package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.account.Account
import com.gogo.steelbotrun.vkbot.inventory.Inventory
import com.gogo.steelbotrun.vkbot.inventory.items.Item
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeExactly

class InventoryTests: StringSpec ({
	"Inventory should add correct number of items" {
		val testItem =  Item(1, "test Item", hashMapOf(), mutableListOf(), 20)
		val testAccount = Account(Inventory(5, mutableListOf()))
		testAccount.inventory.addItem(testItem, 20)
		testAccount.inventory.size shouldBeExactly (5)

	}
})