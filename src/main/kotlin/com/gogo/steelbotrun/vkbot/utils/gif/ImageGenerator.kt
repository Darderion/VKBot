package com.gogo.steelbotrun.vkbot.utils.gif

import de.cerus.jgif.GifImage
import java.awt.image.BufferedImage
import java.io.File

import javax.imageio.ImageIO


class ImageGenerator {
	companion object {
		fun mergeImages(imageFilePaths: List<String>,
						framerates: List<Int?> = listOf(),
						optionDelays: List<Int?> = listOf(),
						backgroundFilePath: String? = null
		) {
			framerates.forEach { framerate -> optionDelays.forEach { optionDelay ->
				merge(imageFilePaths, framerate, optionDelay, backgroundFilePath = backgroundFilePath) } }
			merge(imageFilePaths, backgroundFilePath = backgroundFilePath)
		}

		fun deepCopy(bi: BufferedImage): BufferedImage {
			val cm = bi.colorModel
			val isAlphaPremultiplied = cm.isAlphaPremultiplied
			val raster = bi.copyData(null)
			return BufferedImage(cm, raster, isAlphaPremultiplied, null)
		}

		fun merge(imageFilePaths: List<String>,
				  framerate: Int? = null,
				  optionDelay: Int? = null,
				  backgroundFilePath: String? = null
		) {
			val image = GifImage()

			image.outputFile = File("./images/fighting_${framerate}_${optionDelay}.gif")

			if (framerate != null) {
				image.setFramerate(framerate)
			}

			if (optionDelay != null) {
				image.setDelay(optionDelay)
			}

			val images = imageFilePaths.map { GifImage(File(it)) }

			val options = images.first()
			val maxFrames = images.minOf { it.frames.size }

			val width = options.firstFrame.width
			val height = options.firstFrame.height

			val background: BufferedImage
			if (backgroundFilePath != null) {
				background = ImageIO.read(File(backgroundFilePath))
			} else {
				background = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
			}

			for(i in 0 until maxFrames) {
				println("Frame ${i}/${maxFrames}")
				// val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
				val bufferedImage = deepCopy(background)
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