package com.gogo.steelbotrun.vkbot.inventory.items

class Item(val id: Int, val name: String, val stats: HashMap<String, Int>, val Effects: MutableList<String>, val maxStack: Int =  1)