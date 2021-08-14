package com.gogo.steelbotrun.vkbot.game.character.bodyparts

import kotlin.Error

class BodyParts<T>(parts: List<T?>) {
	val head:       T?
	val body:       T?
	val legs:       T?
	val leftFoot:   T?
	val rightFoot:  T?
	val leftHand:   T?
	val rightHand:  T?
	val finger1:    T?
	val finger2:    T?
	val finger3:    T?
	val finger4:    T?
	val finger5:    T?
	val finger6:    T?
	val finger7:    T?
	val finger8:    T?
	val finger9:    T?

	constructor(
		head:       T? = null,
		body:       T? = null,
		legs:       T? = null,
		leftFoot:   T? = null,
		rightFoot:  T? = null,
		leftHand:   T? = null,
		rightHand:  T? = null,
		finger1:    T? = null,
		finger2:    T? = null,
		finger3:    T? = null,
		finger4:    T? = null,
		finger5:    T? = null,
		finger6:    T? = null,
		finger7:    T? = null,
		finger8:    T? = null,
		finger9:    T? = null): this(listOf(
			head, body, legs, leftFoot, rightFoot, leftHand, rightHand,
			finger1, finger2, finger3, finger4, finger5, finger6, finger7, finger8, finger9))

	init {
		if (parts.count() != numberOfParts)
			throw Error("Incorrect number of parts: ${parts.count()} != $numberOfParts")
		head =      parts[0]
		body =      parts[1]
		legs =      parts[2]
		leftFoot =  parts[3]
		rightFoot = parts[4]
		leftHand =  parts[5]
		rightHand = parts[6]
		finger1 =   parts[7]
		finger2 =   parts[8]
		finger3 =   parts[9]
		finger4 =   parts[10]
		finger5 =   parts[11]
		finger6 =   parts[12]
		finger7 =   parts[13]
		finger8 =   parts[14]
		finger9 =   parts[15]
	}

	private val parts: List<T?>
		get() = listOf(head, body, legs, leftFoot, rightFoot, leftHand, rightHand,
			finger1, finger2, finger3, finger4, finger5, finger6, finger7, finger8, finger9)

	fun isEmpty() = parts.filterNotNull().isEmpty()

	infix fun intersect(other: BodyParts<T>) = BodyParts(this.parts.zip(other.parts) {
			part1, part2 -> (part1 != null && part2 != null)
	})

	infix fun doesNotIntersectWith(other: BodyParts<T>) = intersect(other).parts.none { it == true }

	infix fun union(other: BodyParts<T>) = BodyParts(this.parts.zip(other.parts) {
			part1, part2 -> (part1 != null || part2 != null)
	})

	fun size() = this.parts.filterNotNull().count()

	companion object {
		val numberOfParts = 16
	}
}
