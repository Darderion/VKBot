package com.gogo.steelbotrun.vkbot.core.graph

// This class in not an interface because Kotlin gives following exception when trying to assign id:
//      Using non-JVM static members protected in the superclass companion is unsupported yet
open class Identifiable {
	val id: Int = getId

	companion object {
		private var getId: Int = 0
		get() = field++
	}
}
