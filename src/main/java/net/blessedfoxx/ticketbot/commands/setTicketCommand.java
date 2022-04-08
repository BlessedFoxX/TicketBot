package net.blessedfoxx.ticketbot.commands;

import net.blessedfoxx.ticketbot.commands.type.ServerCommand;
import net.blessedfoxx.ticketbot.utils.Ticket;
import net.blessedfoxx.ticketbot.utils.TicketManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.Event;

public class setTicketCommand implements ServerCommand {

    @Override
    public void performCommand(Member member, TextChannel channel, Message message, Event event) {
        if (member.hasPermission(Permission.ADMINISTRATOR)) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setDescription("To create a ticket please click the button underneath this message!");

            channel.sendMessageEmbeds(builder.build()).setActionRow(TicketManager.getButtonCreate()).queue();
            message.delete().queue();
        }else{
            channel.sendMessage(Ticket.getNoPermission()).queue();
        }
    }
}
