package com.gogo.steelbotrun.vkbot

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File

val token = File("${System.getProperty("user.dir")}/BotInfo.txt").readLines().first()

class Configuration {
}
