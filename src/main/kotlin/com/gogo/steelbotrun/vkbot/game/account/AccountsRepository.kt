package com.gogo.steelbotrun.vkbot.game.account

class AccountsRepository {
	private val accounts: MutableList<Account> = mutableListOf()

	fun get() = accounts.toList()
}
