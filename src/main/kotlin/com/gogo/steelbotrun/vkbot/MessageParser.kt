package com.gogo.steelbotrun.vkbot

/*
@Component
class MessageParser() {
	fun process(event: EventMessage) {
		val text = event.text
		val fromId = event.fromId
		val players = mutableListOf(fromId)
		val fight = server.findFight(fromId)

		val textParts = text.trim().split(" ")

		var message = "Unknown command"
		message =
			with(text.toLowerCase()) {
				when {
					contains("name") -> {
						val name = text.toLowerCase().split(" ")
							.mapIndexed { index, s -> if (s.contains("name")) index + 1 else -1 }.filter { it != -1 }
							.map { text.toLowerCase().split(" ")[it] }.firstOrNull()
						if (name != null) {
							server.changeName(fromId, name)
							"Account name changed to '${name}'"
						} else {
							message
						}
					}
					fight != null -> {
						// Player is in a fight
						val player = fight.participants.first { it is Player && it.id == fromId }

						when {
							contains("attack") -> {
								val skills = text.toLowerCase().split(" ").filter { player.findMove(it) != null }
								if (skills.count() != 1)
									"Select an attacking move"
								else {
									player.selectMove(skills.first())
									val fightMessage = fight.update()
									if (fightMessage.toLowerCase().contains("round")) {
										players.addAll(fight.participants.filterIsInstance<Player>().map { it.id }
											.filter { it != fromId })
									}
									fightMessage
								}
							}
							contains("hp") -> "HP: ${player.hp}\n"
							contains("moves") -> {
								var moves = "Moves:\n"
								player.moves.forEachIndexed { index, move ->
									moves += "${index + 1}) ${move.name}\n"
								}
								moves
							}
							contains("quit") || contains("exit") -> {
								fight.participants.clear()
								"Exited a fight"
							}
							else -> text
						}
					}
					else -> when {
						contains("fight") -> {
							when {
								contains("new") -> {
									when {
										contains("bot") -> server.fights.add(
											Fight(
												mutableListOf(
													BasicMonster(
														"Bandit",
														listOf(
															com.gogo.steelbotrun.vkbot.battle.actions.Area.Head,
															com.gogo.steelbotrun.vkbot.battle.actions.Area.Body,
															com.gogo.steelbotrun.vkbot.battle.actions.Area.Legs
														),
														listOf(
															Move(
																"Bandit's attack",
																listOf(
																	SimpleAction(
																		com.gogo.steelbotrun.vkbot.battle.actions.ActionType.Attack,
																		com.gogo.steelbotrun.vkbot.battle.actions.Area.Body,
																		10
																	)
																)
															)
														)
													),
													Player("Vasya", fromId)
												)
											)
										)
										contains("player") -> server.fights.first { it.participants.none { it is Monster } }.participants.add(
											Player("Vasyan", fromId)
										)
									}
									"Entered a fight"
								}
								else -> text
							}
						}
						else -> text
					}
				}
			}
		SDK.send(message, players)
	}
}
 */
