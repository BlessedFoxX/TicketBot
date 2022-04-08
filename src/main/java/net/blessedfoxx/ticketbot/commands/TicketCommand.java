package net.blessedfoxx.ticketbot.commands;

import net.blessedfoxx.ticketbot.commands.type.ServerCommand;
import net.blessedfoxx.ticketbot.utils.Ticket;
import net.blessedfoxx.ticketbot.utils.TicketManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.Event;

import java.util.List;

public class TicketCommand implements ServerCommand {

    @Override
    public void performCommand(Member member, TextChannel channel, Message message, Event event) {

        if (TicketManager.isTicketBanned(member) && !member.hasPermission(Permission.ADMINISTRATOR)) {
            channel.sendMessage(Ticket.getTicketBanned()).queue();
            return;
        }

        String[] args = message.getContentRaw().split(" ");

        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("create")) {
                TicketManager.createTicket(member);
                channel.sendMessage(":white_check_mark: Your ticket " + TicketManager.getTicketChannel().getAsMention() +" has been created!").queue();
            }else if (args[1].equalsIgnoreCase("claim")) {
                TicketManager.claimTicket(member, channel);
            }
            else if (args[1].equalsIgnoreCase("close")) {
                TicketManager.deleteTicket(channel);
            }else{
                Ticket.Usage(channel, "ticket <create/close>");
            }
        }

        else if (args.length == 3) {
            if (args[1].equalsIgnoreCase("add")) {
                List<Member> getMember = message.getMentionedMembers();

                if (!getMember.isEmpty()) {

                    for (Member addMember : getMember) {
                        TicketManager.addUserToTicket(addMember, channel);
                    }
                }else{
                    channel.sendMessage(Ticket.getNoMemberFound()).queue();
                }
            }

            else if (args[1].equalsIgnoreCase("remove")) {
                List<Member> getMember = message.getMentionedMembers();

                if (!getMember.isEmpty()) {

                    for (Member addMember : getMember) {
                        TicketManager.removeUserFromTicket(addMember, channel);
                    }
                }else{
                    channel.sendMessage(Ticket.getNoMemberFound()).queue();
                }
            }

            else if (args[1].equalsIgnoreCase("ban")) {
                List<Member> getMember = message.getMentionedMembers();

                if (!getMember.isEmpty()) {

                    for (Member addMember : getMember) {
                        TicketManager.ticketBan(addMember, channel);
                    }
                }else{
                    channel.sendMessage(Ticket.getNoMemberFound()).queue();
                }
            }

            else if (args[1].equalsIgnoreCase("unban")) {
                List<Member> getMember = message.getMentionedMembers();

                if (!getMember.isEmpty()) {

                    for (Member addMember : getMember) {
                        TicketManager.ticketUnban(addMember, channel);
                    }
                }else{
                    channel.sendMessage(Ticket.getNoMemberFound()).queue();
                }
            }

            else if (args[1].equalsIgnoreCase("create")) {
                List<Member> getMember = message.getMentionedMembers();

                if (!getMember.isEmpty()) {

                    for (Member createMember : getMember) {
                        TicketManager.createTicket(createMember);
                    }
                }else{
                    channel.sendMessage(Ticket.getNoMemberFound()).queue();
                }
            }else{
                Ticket.Usage(channel, "ticket <create/ban/unban> <user>");
            }


        }else{
            Ticket.Usage(channel, "ticket <create/close>");
        }

    }

}
