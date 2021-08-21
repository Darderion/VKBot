package com.gogo.steelbotrun.vkbot.core.graph

// This class in not an interface because Kotlin gives following exception when trying to assign id:
//      Using non-JVM static members protected in the superclass companion is unsupported yet
open class Identifiable {
	val id: Int = getId

	override fun equals(other: Any?) = if (other != null && other is Identifiable) id == other.id else false
	override fun hashCode() = id

	companion object {
		private var getId: Int = 0
		get() = field++
	}
}
