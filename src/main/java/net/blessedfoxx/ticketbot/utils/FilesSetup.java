package net.blessedfoxx.ticketbot.utils;

import java.io.*;

public class FilesSetup {

    public static void test(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        File file = new File(loader.getResource("config/application.properties").getFile());

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
