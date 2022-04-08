package net.blessedfoxx.ticketbot;

import net.blessedfoxx.ticketbot.events.ButtonUseEvent;
import net.blessedfoxx.ticketbot.events.CommandEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class TicketBot {

    private static JDA jda;
    private static TicketBot INSTANCE;
    private CommandManager commandManager;

    public static void main(String[] args) {

        try {
            new TicketBot();
        }catch (LoginException | IllegalArgumentException exception) {
            exception.printStackTrace();
        }

    }



    public TicketBot() throws LoginException, IllegalArgumentException {
        INSTANCE = this;

        JDABuilder builder = JDABuilder.createDefault("TOKEN HERE");

        builder.setActivity(Activity.playing("your Mum!"));
        builder.setStatus(OnlineStatus.IDLE);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);

        builder.addEventListeners(new CommandEvent());
        builder.addEventListeners(new ButtonUseEvent());


        commandManager = new CommandManager();
        jda = builder.build();
    }

    public static JDA getJda() {
        return jda;
    }

    public static TicketBot getINSTANCE() {
        return INSTANCE;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

}
