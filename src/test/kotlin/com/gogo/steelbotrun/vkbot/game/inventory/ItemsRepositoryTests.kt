package com.gogo.steelbotrun.vkbot.game.inventory

import com.gogo.steelbotrun.vkbot.game.character.bodyparts.BodyParts
import com.gogo.steelbotrun.vkbot.game.character.stats.Stats
import com.gogo.steelbotrun.vkbot.game.inventory.item.ItemRepository
import com.gogo.steelbotrun.vkbot.game.inventory.item.ItemType
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ItemsRepositoryTests: StringSpec({
	"ItemRepository should return items" {
		val itemRepository = ItemRepository()
		itemRepository.items().count() shouldBe 3
		val item1 = itemRepository.items()[0]
		val item2 = itemRepository.items()[1]
		val item3 = itemRepository.items()[2]

		item1.name shouldBe "Sword from Tina's DLC"
		item2.name shouldBe "Bow from Tina's DLC"

		item1.description shouldBe "Cool sword"
		item2.description shouldBe "Cool bow"

		(item1.stats == Stats("Strength" to 1.0, "Agility" to 2.0)) shouldBe true
		(item2.stats == Stats("Agility" to 4.0)) shouldBe true

		item1.type shouldBe ItemType.Equipment
		item3.type shouldBe ItemType.Consumable

		item1.canUse(BodyParts<Boolean>(), Stats("Strength" to 3.0, "Agility" to 4.0)) shouldBe false
		item1.canUse(BodyParts<Boolean>(), Stats("Strength" to 4.0)) shouldBe false
		item1.canUse(BodyParts<Boolean>(), Stats("Strength" to 4.0, "Agility" to 4.0)) shouldBe true
	}
})
