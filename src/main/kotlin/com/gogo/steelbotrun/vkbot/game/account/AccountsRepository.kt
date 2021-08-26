package com.gogo.steelbotrun.vkbot.game.account

class AccountsRepository {
	private val accounts: MutableList<Account> = mutableListOf()

	fun get() = accounts.toList()

	fun add(account: Account): Boolean {
		if (accounts.none { it.id == account.id }) {
			accounts.add(account)
			return true
		}
		return false
	}
}
