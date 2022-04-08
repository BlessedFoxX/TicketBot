package net.blessedfoxx.ticketbot.events;

import net.blessedfoxx.ticketbot.utils.TicketManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

public class ButtonUseEvent extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        Member member = event.getMember();
        Button button = event.getButton();

        if (button.getId().equalsIgnoreCase("claim")) {
            TicketManager.claimByButtonTicket(member, event);
        }else if (button.getId().equalsIgnoreCase("close")) {
            event.deferReply(true).setContent(":x: Ticket Closed!").queue();
            TicketManager.deleteTicket(event.getTextChannel());
        }else if (button.getId().equalsIgnoreCase("create")) {
            if (member !=null)
                TicketManager.createTicket(member);
                event.getInteraction().deferReply(true).setContent(":white_check_mark: Your ticket " + TicketManager.getTicketChannel().getAsMention() +" has been created!").queue();
        }
    }
}
