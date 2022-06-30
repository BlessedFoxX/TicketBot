/*
 *        ╭━━━━╮╱╱╭╮╱╱╱╱╭╮╱╭━━╮╭━━━┳━━━━╮
 *        ┃╭╮╭╮┃╱╱┃┃╱╱╱╭╯╰╮┃╭╮┃┃╭━╮┃╭╮╭╮┃
 *        ╰╯┃┃┣╋━━┫┃╭┳━┻╮╭╯┃╰╯╰┫┃╱┃┣╯┃┃╰╯
 *        ╱╱┃┃┣┫╭━┫╰╯┫┃━┫┃╱┃╭━╮┃┃╱┃┃╱┃┃
 *        ╱╱┃┃┃┃╰━┫╭╮┫┃━┫╰╮┃╰━╯┃╰━╯┃╱┃┃
 *        ╱╱╰╯╰┻━━┻╯╰┻━━┻━╯╰━━━┻━━━╯╱╰╯
 *
 *       Copyright (C) 2022 - 2026 BlessedFoxX
 */
package net.blessedfoxx.ticketbot.commands.type;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface SlashCommand {

    public void performCommand(SlashCommandInteractionEvent e, Member m, TextChannel channel);
}