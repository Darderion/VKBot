package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.gif.ImageGenerator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VkbotApplication

fun main(args: Array<String>) {
	val generateImage = false

	if (generateImage) {
		ImageGenerator.mergeImages(
			listOf(
				"images/1.gif",
				"images/3.gif",
				"images/4.gif"
			), listOf(3), listOf(null)
		)
	} else {
		runApplication<VkbotApplication>(*args)
	}
}
