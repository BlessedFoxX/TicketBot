package net.blessedfoxx.ticketbot.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import net.blessedfoxx.ticketbot.TicketBot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class FileManager {
    /*public static LinkedTreeMap values = new LinkedTreeMap();

    public FileManager() throws IOException {
        File folder = new File("settings/");
        if (!folder.exists())
            folder.mkdir();
        File file = new File("settings/config.json");
        if (!file.exists()) {
            file.createNewFile();
            Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
            values.clear();
            values.put("token", "TOPSECRETTOKEN");
            values.put("categoryID", "EMPTY");
            values.put("everyoneID", "EMPTY");
            values.put("guildID", "EMPTY");
            values.put("teamRoleID", "EMPTY");
            values.put("bannedRoleID", "EMPTY");
            FileWriter fw = new FileWriter(file);
            fw.write(gson.toJson(values));
            fw.flush();
            fw.close();
            values.clear();
            String string = "";
            try {
                string = new String(Files.readAllBytes(file.toPath()));
            } catch (IOException var6) {
                var6.printStackTrace();
            }
            if (!string.isEmpty()) {
                LinkedTreeMap jsonObject = (LinkedTreeMap)(new Gson()).fromJson(string, LinkedTreeMap.class);
                String gsons = (new Gson()).toJson(jsonObject);
                values = (LinkedTreeMap)(new Gson()).fromJson(gsons, LinkedTreeMap.class);
            }
        } else {
            values.clear();
            String string = "";
            try {
                string = new String(Files.readAllBytes(file.toPath()));
            } catch (IOException var6) {
                var6.printStackTrace();
            }
            if (!string.isEmpty()) {
                LinkedTreeMap jsonObject = (LinkedTreeMap)(new Gson()).fromJson(string, LinkedTreeMap.class);
                String gsons = (new Gson()).toJson(jsonObject);
                values = (LinkedTreeMap)(new Gson()).fromJson(gsons, LinkedTreeMap.class);
            }

            TicketBot.token = ((String)values.get("token"));
            Ticket.categoryID = ((String)values.get("categoryID"));
            Ticket.everyoneRoleID = ((String)values.get("everyoneID"));
            Ticket.guildID = ((String)values.get("guildID"));
            Ticket.teamRoleID = ((String)values.get("teamRoleID"));
            Ticket.bannedRoleID = ((String)values.get("bannedRoleID"));
        }
    }

    public static void save() throws IOException {
        File file = new File("settings/config.json");
        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        FileWriter fw = new FileWriter(file);
        values.put("token", TicketBot.token);
        values.put("categoryID", Ticket.categoryID);
        values.put("everyoneID", Ticket.everyoneRoleID);
        values.put("guildID", Ticket.guildID);
        values.put("teamRoleID", Ticket.teamRoleID);
        values.put("bannedRoleID", Ticket.bannedRoleID);
        fw.write(gson.toJson(values));
        fw.flush();
        fw.close();
    }*/
}