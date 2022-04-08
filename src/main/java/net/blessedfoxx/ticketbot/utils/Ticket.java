package net.blessedfoxx.ticketbot.utils;

import net.blessedfoxx.ticketbot.CommandManager;
import net.blessedfoxx.ticketbot.TicketBot;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

public class Ticket {

    private static String notInTicket = ":exclamation: You are not on a ticket!";
    private static String noPermission = ":x: You don't have permission to do this!";
    private static String ticketBanned = ":x: You are Ticket Banned!";
    private static String noMemberFound = ":interrobang: The user was not found!";

    private static Guild guild = TicketBot.getJda().getGuildById("959936404425687120");
    private static Category category = guild.getCategoryById("962012288959803452");

    private static Role teamRole = guild.getRoleById("960928085472051211");
    private static Role everyoneRole = guild.getRoleById("959936404425687120");
    private static Role bannedRole = guild.getRoleById("962012596926554112");

    public static String getNotInTicket() {
        return notInTicket;
    }

    public static String getNoPermission() {
        return noPermission;
    }

    public static Category getCategory() {
        return category;
    }

    public static Role getTeamRole() {
        return teamRole;
    }

    public static Role getEveryoneRole() {
        return everyoneRole;
    }

    public static Role getBannedRole() {
        return bannedRole;
    }

    public static String getTicketBanned() {
        return ticketBanned;
    }

    public static String getNoMemberFound() {
        return noMemberFound;
    }

    public static void Usage(TextChannel channel, String usage) {
        channel.sendMessage(":interrobang: Usage: `" + CommandManager.getPrefix() + usage + "`").queue();
    }

}
