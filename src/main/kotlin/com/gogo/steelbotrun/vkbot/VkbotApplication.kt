package com.gogo.steelbotrun.vkbot

import com.gogo.steelbotrun.vkbot.gif.ImageGenerator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class VkbotApplication

fun main(args: Array<String>) {
	ImageGenerator.merge(listOf(
		"images/1.gif",
		"images/2.gif"
	))

	// runApplication<VkbotApplication>(*args)
}
