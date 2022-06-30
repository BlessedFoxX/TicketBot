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
package net.blessedfoxx.ticketbot.utils;

import net.blessedfoxx.ticketbot.TicketBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.TimeFormat;

public class TicketManager {

    private static TextChannel ticketChannel;
    private static Button buttonClaim = Button.secondary("claim", "Claim").withEmoji(Emoji.fromUnicode("\uD83D\uDD12"));
    private static Button buttonClose = Button.secondary("close", "Close").withEmoji(Emoji.fromUnicode("\uD83D\uDCA2"));


    private static Button buttonCreate = Button.secondary("create", "Create Ticket").withEmoji(Emoji.fromUnicode("\uD83D\uDCE9"));

    public static void createTicket(Member member, ButtonInteractionEvent event) {

        if (!Ticket.getCategory().getTextChannels().contains(TicketBot.getJda().getTextChannelsByName("ticket-" + member.getUser().getAsTag().replaceAll("#" , "♯"), true))) {

            ticketChannel = Ticket.getCategory().createTextChannel("ticket-" + member.getUser().getAsTag().replaceAll("#", "♯")).complete();

            setPermissionsRole(false, Ticket.getEveryoneRole(), ticketChannel);
            setPermissionsRole(true, Ticket.getTeamRole(), ticketChannel);
            setPermissionsUser(true, member, ticketChannel);

            EmbedBuilder builder = new EmbedBuilder();
            builder.setDescription(":interrobang: Your ticket will be processed shortly! Please send us your request so that we can offer you better support.");
            builder.setThumbnail(member.getEffectiveAvatarUrl());
            builder.addField(":timer: _*Created at:*_", String.valueOf(TimeFormat.RELATIVE.now()), true);
            builder.addField(":bust_in_silhouette: _*Created by:*_", member.getUser().getAsTag(), true);


            ticketChannel.sendMessage("The " + Ticket.getTeamRole().getAsMention() + " has been notified!")
                    .setEmbeds(builder.build())
                    .setActionRow(buttonClaim.asEnabled(),
                            buttonClose.asEnabled())
                    .queue();
        }else{
            event.deferReply().setEphemeral(true).setContent(":x: You are already in a Ticket! :x:").queue();
        }

    }

    public static void createTicket(Member member) {

        if (!Ticket.getCategory().getTextChannels().contains(TicketBot.getJda().getTextChannelsByName("ticket-" + member.getUser().getAsTag().replaceAll("#" , "♯"), true))) {

            ticketChannel = Ticket.getCategory().createTextChannel("ticket-" + member.getUser().getAsTag().replaceAll("#", "♯")).complete();

            setPermissionsRole(false, Ticket.getEveryoneRole(), ticketChannel);
            setPermissionsRole(true, Ticket.getTeamRole(), ticketChannel);
            setPermissionsUser(true, member, ticketChannel);

            EmbedBuilder builder = new EmbedBuilder();
            builder.setDescription(":interrobang: Your ticket will be processed shortly! Please send us your request so that we can offer you better support.");
            builder.setThumbnail(member.getEffectiveAvatarUrl());
            builder.addField(":timer: _*Created at:*_", String.valueOf(TimeFormat.RELATIVE.now()), true);
            builder.addField(":bust_in_silhouette: _*Created by:*_", member.getUser().getAsTag(), true);


            ticketChannel.sendMessage("The " + Ticket.getTeamRole().getAsMention() + " has been notified!")
                    .setEmbeds(builder.build())
                    .setActionRow(buttonClaim.asEnabled(),
                            buttonClose.asEnabled())
                    .queue();
        }

    }

    private static void setPermissionsUser(boolean allowOrDeny, Member member, TextChannel channel) {
        if (allowOrDeny) {
            channel.getManager().getChannel().upsertPermissionOverride(member).setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY, Permission.MESSAGE_SEND).queue();
        }else{
            channel.getManager().getChannel().upsertPermissionOverride(member).setDeny(Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY, Permission.MESSAGE_SEND).queue();
        }
    }

    private static void setPermissionsRole(boolean allowOrDeny, Role role, TextChannel channel) {
        if (allowOrDeny) {
            channel.getManager().getChannel().upsertPermissionOverride(role).setAllow(Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY, Permission.MESSAGE_SEND).queue();
        }else{
            channel.getManager().getChannel().upsertPermissionOverride(role).setDeny(Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY, Permission.MESSAGE_SEND).queue();
        }
    }

    public static void deleteTicket(TextChannel channel) {
        if (isTicket(channel)) {
            channel.delete().queue();
        }else{
            channel.sendMessage(Ticket.getNotInTicket()).queue();
        }
    }

    public static void addUserToTicket(Member member, TextChannel channel) {
        if (isTeam(member)) {

            if (isTicket(channel)) {
                setPermissionsUser(true, member, channel);
            }else{
                channel.sendMessage(Ticket.getNotInTicket()).queue();
            }
        }else{
            channel.sendMessage(Ticket.getNoPermission()).queue();
        }
    }

    public static void removeUserFromTicket(Member member, TextChannel channel) {
        if (isTeam(member)) {

            if (isTicket(channel)) {
                setPermissionsUser(false, member, channel);
            }else {
                channel.sendMessage(Ticket.getNotInTicket()).queue();
            }
        }else{
            channel.sendMessage(Ticket.getNoPermission()).queue();
        }
    }

    public static void claimTicket(Member member, TextChannel channel) {
        if (isTeam(member)) {

            setPermissionsRole(false, Ticket.getTeamRole(), channel);
            setPermissionsUser(true, member, channel);

            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor("The user " + member.getUser().getAsTag() + " is now processing your ticket!", null, member.getEffectiveAvatarUrl());
            channel.sendMessageEmbeds(builder.build()).queue();

        }else{
            channel.sendMessage(Ticket.getNoPermission()).queue();
        }
    }

    public static void claimByButtonTicket(Member member, ButtonInteractionEvent button) {
        if (isTeam(member)) {

            setPermissionsRole(false, Ticket.getTeamRole(), button.getTextChannel());

            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor("The user " + member.getUser().getAsTag() + " is now processing your ticket!", null, member.getEffectiveAvatarUrl());
            button.replyEmbeds(builder.build()).queue();

            button.getInteraction().editButton(TicketManager.getButtonClaim().asDisabled()).queue();

        }else{
            button.reply(Ticket.getNoPermission()).setEphemeral(true).queue();
        }
    }

    public static void ticketBan(Member member, TextChannel channel) {
        if (!isTicketBanned(member)) {
            member.getGuild().addRoleToMember(member.getIdLong(), Ticket.getBannedRole()).queue();
            channel.sendMessage("The user `" + member.getUser().getAsTag() + "` is now ticket banned!").queue();
        }else{
            channel.sendMessage(":x: This User is already banned!").queue();
        }
    }

    public static void ticketUnban(Member member, TextChannel channel) {
        if (isTicketBanned(member)) {
            member.getGuild().removeRoleFromMember(member.getIdLong(), Ticket.getBannedRole()).queue();
            channel.sendMessage("The user `" + member.getUser().getAsTag() + "` is now ticket unbanned!").queue();
        }else{
            channel.sendMessage(":x: This User is not banned!").queue();
        }
    }

    public static TextChannel getTicketChannel() {
        return ticketChannel;
    }

    public static boolean isTicket(TextChannel textChannel) {
        return textChannel.getParentCategory().equals(Ticket.getCategory());
    }

    public static boolean isTeam(Member member) {
        return member.getRoles().contains(Ticket.getTeamRole()) || member.hasPermission(Permission.ADMINISTRATOR);
    }

    public static boolean isTicketBanned(Member member) {
        return member.getRoles().contains(Ticket.getBannedRole());
    }

    public static Button getButtonClaim() {
        return buttonClaim;
    }

    public static Button getButtonClose() {
        return buttonClose;
    }

    public static Button getButtonCreate() {
        return buttonCreate;
    }
}
