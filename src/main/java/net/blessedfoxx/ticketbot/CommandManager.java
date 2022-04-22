package net.blessedfoxx.ticketbot;

import net.blessedfoxx.ticketbot.commands.AddRoleCommand;
import net.blessedfoxx.ticketbot.commands.TicketCommand;
import net.blessedfoxx.ticketbot.commands.setTicketCommand;
import net.blessedfoxx.ticketbot.commands.type.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.Event;

import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

    public ConcurrentHashMap<String, ServerCommand> commands;
    private static String prefix = "t!";

    public CommandManager() {
        this.commands = new ConcurrentHashMap<>();

        this.commands.put("ticket", new TicketCommand());
        this.commands.put("setticket", new setTicketCommand());
        this.commands.put("addrole", new AddRoleCommand());

    }

    public boolean perform(String command, Member m, TextChannel channel, Message message, Event event) {

        ServerCommand cmd;
        if((cmd = this.commands.get(command.toLowerCase())) != null) {
            cmd.performCommand(m, channel, message, event);
            return true;
        }
        return false;
    }

    public static String getPrefix() {
        return prefix;
    }
}