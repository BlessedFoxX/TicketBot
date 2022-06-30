package net.blessedfoxx.ticketbot.events;

import net.blessedfoxx.ticketbot.utils.Ticket;
import net.blessedfoxx.ticketbot.utils.TranscriptManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class TicketMessageAddTranscript extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (Ticket.getCategory().getId().equals(event.getTextChannel().getParentCategoryId())) {
            TranscriptManager.writeInTranscript(event.getTextChannel(), event.getMessage());
        }
    }
}
