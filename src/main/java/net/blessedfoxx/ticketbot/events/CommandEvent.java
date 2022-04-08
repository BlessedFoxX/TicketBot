package net.blessedfoxx.ticketbot.events;

import net.blessedfoxx.ticketbot.CommandManager;
import net.blessedfoxx.ticketbot.TicketBot;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class CommandEvent extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        String message = event.getMessage().getContentDisplay();


        if (event.isFromType(ChannelType.TEXT)) {
            TextChannel channel = event.getTextChannel();


            if (message.startsWith(CommandManager.getPrefix())) {
                String[] args = message.substring(CommandManager.getPrefix().length()).split(" ");

                if (args.length > 0) {

                    if (!TicketBot.getINSTANCE().getCommandManager().perform(args[0], event.getMember(), channel, event.getMessage(), event)) {
                        event.getMessage().reply(":interrobang: This command could not be found!").complete().delete().queueAfter(3, TimeUnit.SECONDS);
                    }
                }
            }
        }
    }
}