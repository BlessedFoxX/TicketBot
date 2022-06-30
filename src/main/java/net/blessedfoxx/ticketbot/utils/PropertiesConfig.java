package net.blessedfoxx.ticketbot.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class PropertiesConfig {

    public static String getToken = null;
    public static String getCategory = null;
    public static String getEveryoneRole = null;
    public static String getGuildID = null;
    public static String getTeamRole = null;
    public static String getBannedRole = null;

    public PropertiesConfig() throws IOException {


        File file = new File("application.properties");
        if (!file.exists()) {



        } else {

            Properties properties = new Properties();
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream("application.properties"));

            properties.load(stream);
            stream.close();


            getToken = properties.getProperty("bot.token");
            getCategory = properties.getProperty("bot.categoryGroup");
            getEveryoneRole = properties.getProperty("bot.everyoneRole");
            getGuildID = properties.getProperty("bot.guildID");
            getTeamRole = properties.getProperty("bot.teamRole");
            getBannedRole = properties.getProperty("bot.bannedRole");
        }
    }

    public static void printFile(File file) {

        List<String> lines;

        try {
            System.out.println("\n========================\n");
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
            System.out.println("\n========================\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}