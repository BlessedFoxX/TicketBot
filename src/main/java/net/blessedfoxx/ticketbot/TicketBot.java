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

package net.blessedfoxx.ticketbot;

import me.tongfei.progressbar.ProgressBar;
import net.blessedfoxx.ticketbot.events.ButtonUseEvent;
import net.blessedfoxx.ticketbot.events.CommandEvent;
import net.blessedfoxx.ticketbot.events.TicketMessageAddTranscript;
import net.blessedfoxx.ticketbot.utils.Bot;
import net.blessedfoxx.ticketbot.utils.PropertiesConfig;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.*;

public class TicketBot {

    private static JDA jda;
    private static TicketBot INSTANCE;
    private CommandManager commandManager;
    public static final Runtime runtime = Runtime.getRuntime();

    public String status = "soon";

    public static void main(String[] args) {

        sendLogo();
        try {

            new PropertiesConfig();
            new TicketBot();
        }catch (LoginException | IllegalArgumentException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public TicketBot() throws LoginException, IllegalArgumentException {
        INSTANCE = this;

        // "OTc5ODcwMzE2MDM5OTI1Nzky.G56rV5.d0xsnOEPezp_H5wvI_8J9RtzC5BkWFccRO96lM"



        JDABuilder builder = JDABuilder.createDefault(PropertiesConfig.getToken);

        builder.setActivity(Activity.playing("Developed by Angekotzter"));
        builder.setStatus(OnlineStatus.IDLE);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);

        try (ProgressBar pb1 = new ProgressBar(Bot.PREFIX + "RegisterEvents", 3)) {

            builder.addEventListeners(new CommandEvent());
            pb1.stepBy(1);
            builder.addEventListeners(new ButtonUseEvent());
            pb1.stepBy(2);
            builder.addEventListeners(new TicketMessageAddTranscript());
            pb1.stepBy(3);

        }


        commandManager = new CommandManager();
        jda = builder.build();
        System.out.println(Bot.PREFIX + Bot.TIME + "Current Guilds | " + TicketBot.jda.getGuildCache().size());

        shutdown();
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

    private static void sendLogo() {

        double totalMemory = TicketBot.runtime.totalMemory();
        String runtime_alloc = convert(totalMemory);

        System.out.println("");
        System.out.println("    ╭━━━━╮╱╱╭╮╱╱╱╱╭╮╱"+Bot.ANSI_YELLOW +"╭━━╮╭━━━┳━━━━╮" + Bot.ANSI_RESET);
        System.out.println("    ┃╭╮╭╮┃╱╱┃┃╱╱╱╭╯╰╮"+Bot.ANSI_YELLOW +"┃╭╮┃┃╭━╮┃╭╮╭╮┃"+ Bot.ANSI_RESET);
        System.out.println("    ╰╯┃┃┣╋━━┫┃╭┳━┻╮╭╯"+Bot.ANSI_YELLOW +"┃╰╯╰┫┃╱┃┣╯┃┃╰╯"+ Bot.ANSI_RESET);
        System.out.println("    ╱╱┃┃┣┫╭━┫╰╯┫┃━┫┃╱"+Bot.ANSI_YELLOW +"┃╭━╮┃┃╱┃┃╱┃┃"+ Bot.ANSI_RESET);
        System.out.println("    ╱╱┃┃┃┃╰━┫╭╮┫┃━┫╰╮"+Bot.ANSI_YELLOW +"┃╰━╯┃╰━╯┃╱┃┃"+ Bot.ANSI_RESET);
        System.out.println("    ╱╱╰╯╰┻━━┻╯╰┻━━┻━╯"+Bot.ANSI_YELLOW +"╰━━━┻━━━╯╱╰╯"+ Bot.ANSI_RESET);
        System.out.println("" + Bot.ANSI_RESET);
        System.out.println(" Copyright (C) 2022 - 2026 BlessedFoxX");
        System.out.println("");

        System.out.println(Bot.PREFIX + Bot.TIME + "Reserved Memory | " + runtime_alloc);
    }

    private static String convert(Double value) {
        String parsed;
        if (value < 1000000000) {
            double data = Math.round(value / 1048576);
            parsed = String.format("%s MB", data);
            return parsed;
        }
        double data = Math.round(value / 1073741824);
        parsed = String.format("%s GB", data);
        return parsed;
    }

    public static void shutdown() {

        (new Thread(() -> { String line = "";

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase("!exit")) {
                        TicketBot.jda.getPresence().setStatus(OnlineStatus.OFFLINE);
                        System.out.println(Bot.PREFIX + "Bye bye! ఠ_ఠ");
                        reader.close();
                        System.exit(1);
                        continue;
                    }else if(line.equalsIgnoreCase("!showconfig")){
                        File file = new File("application.properties");
                        PropertiesConfig.printFile(file);
                    }

                    System.out.println(Bot.PREFIX + "Wanna leave? Type: '!exit' !");

                }

            } catch (IOException e) {

                e.printStackTrace();

            }

        })).start();

    }

}
