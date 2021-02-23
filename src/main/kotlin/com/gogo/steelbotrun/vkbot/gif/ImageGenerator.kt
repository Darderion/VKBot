package com.gogo.steelbotrun.vkbot.gif

import de.cerus.jgif.GifImage
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException

import java.io.FileNotFoundException

import javax.imageio.stream.FileImageOutputStream

import javax.imageio.stream.ImageOutputStream

import java.io.FileInputStream




class ImageGenerator {
	companion object {
		fun merge(imageFilePaths: List<String>) {
			val image = GifImage()

			image.outputFile = File("./images/fighting.gif")

			val images = imageFilePaths.map { GifImage(File(it)) }

			val options = images.first()
			val maxFrames = images.minOf { it.frames.size }

			println("Frames: ${maxFrames}")

			val width = options.firstFrame.width
			val height = options.firstFrame.height

			println("Width: ${width}")
			println("Height: ${height}")

			for(i in 0 until maxFrames) {
				println("Frame ${i}/${maxFrames}")
				val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
				val graphics = bufferedImage.graphics

				images.forEach {
					val frame = it.getFrame(i)
					graphics.drawImage(frame, 0, 0, null)
				}

				graphics.dispose()

				image.addFrame(bufferedImage)
				// image.setFrame(i, bufferedImage)
			}

			image.repeatInfinitely(true)

			image.saveAllFrames(File("images/frames"))
			image.save()
		}
	}
}