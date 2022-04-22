package net.blessedfoxx.ticketbot.commands;

import net.blessedfoxx.ticketbot.commands.type.ServerCommand;
import net.blessedfoxx.ticketbot.utils.Ticket;
import net.blessedfoxx.ticketbot.utils.UserManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.Event;

import java.util.List;

public class AddRoleCommand implements ServerCommand {

    @Override
    public void performCommand(Member member, TextChannel channel, Message message, Event event) {
        String[]args = message.getContentRaw().split(" ");

        if (member.hasPermission(Permission.MANAGE_ROLES)) {

        if (args.length == 3) {
            List<Member> members = message.getMentionedMembers();

            if (!members.isEmpty()) {
                for (Member targetMember : members){
                    List<Role> roles = message.getMentionedRoles();

                    if (!roles.isEmpty()) {
                        for (Role role : roles) {

                            UserManager.addRoleToUser(targetMember, role.getIdLong());
                            channel.sendMessage("Added role the Role " + role.getAsMention() + " to " + targetMember.getAsMention() + "!").queue();
                        }
                    }else{
                        Ticket.Usage(channel, "addrole <@User> <@Role>");
                    }
                 }
            }else{
                Ticket.Usage(channel, "addrole <@User> <@Role>");
            }
        }else {
            Ticket.Usage(channel, "addrole <@User> <@Role>");
            }
        }else{
            channel.sendMessage(Ticket.getNoPermission()).queue();
        }
    }
}
