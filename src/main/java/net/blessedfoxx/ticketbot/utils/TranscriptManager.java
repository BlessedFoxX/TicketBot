package net.blessedfoxx.ticketbot.utils;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import java.io.File;
import java.io.FileWriter;

public class TranscriptManager {

    public static void writeInTranscript(TextChannel channel, Message message) {
        File transcript = new File("transcript/" + channel.getName() + ".txt");

        if (!transcript.exists()) {
            try {
                transcript.createNewFile();
            }catch (Exception e) {

            }
        }


        try {
            FileWriter writer = new FileWriter(channel.getName().replaceAll("#" , "♯") + ".txt");
            writer.write(Bot.TIME + " " + message.getAuthor().getAsTag() + " " + message.getContentRaw());
            writer.close();

            System.out.println("[CHAT-LOG] " + Bot.TIME + " " + message.getAuthor().getAsTag() + " " + message.getContentRaw());
        }catch (Exception exception) {
            System.out.println("[CHAT-LOG] " + "Failed saving files!");
        }
    }


}
