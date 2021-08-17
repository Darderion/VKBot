package com.gogo.steelbotrun.vkbot.game.inventory

import com.gogo.steelbotrun.vkbot.game.character.stats.Stats
import com.gogo.steelbotrun.vkbot.game.inventory.item.ItemRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ItemsTests: StringSpec({
	"ItemRepository should return items" {
		val itemRepository = ItemRepository()
		itemRepository.items().count() shouldBe 2
		val item1 = itemRepository.items()[0]
		val item2 = itemRepository.items()[1]

		item1.name shouldBe "Sword from Tina's DLC"
		item2.name shouldBe "Bow from Tina's DLC"

		(item1.stats == Stats("Strength" to 1.0, "Agility" to 2.0)) shouldBe true
		(item2.stats == Stats("Agility" to 4.0)) shouldBe true
	}
}) {
}