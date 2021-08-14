package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.core.graph.Identifiable
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class IdentifiableTests : StringSpec({
	"Identifiables should be equal if they have the same id" {
		val identifiableObject1 = Identifiable()
		val identifiableObject2 = Identifiable()
		val identifiableObject3 = identifiableObject1

		identifiableObject1 shouldBe identifiableObject3
		identifiableObject1 shouldNotBe identifiableObject2
	}
})
