package net.blessedfoxx.ticketbot.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Bot {

    public static String getUptime() {

        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        long uptimeMS = rb.getUptime();

        long uptimeDAYS = TimeUnit.MILLISECONDS.toDays(uptimeMS);
        uptimeMS -= TimeUnit.DAYS.toMillis(uptimeDAYS);

        long uptimeHRS = TimeUnit.MILLISECONDS.toHours(uptimeMS);
        uptimeMS -= TimeUnit.HOURS.toMillis(uptimeHRS);

        long uptimeMIN = TimeUnit.MILLISECONDS.toMinutes(uptimeMS);

        uptimeMS -= TimeUnit.MINUTES.toMillis(uptimeMIN);
        long uptimeSEC = TimeUnit.MILLISECONDS.toSeconds(uptimeMS);

        return String.format("%sd %sh %smin %ss",
                uptimeDAYS, uptimeHRS, uptimeMIN, uptimeSEC);
    }

    public static String TOKEN = "OTU5MzU1MjU2MzcyODY3MDg0.YkarNQ.OvuRlGXre0XWM1es447apnt1YjA";

    public static String TestTOKEN = "OTUwMDg5NTM2MjkyMTQzMTE1.YiT11Q.0U1E3GoEC1GnOfyZ_hl5kGTUOso";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String PREFIX = "[" + ANSI_YELLOW + "Ticket" + ANSI_RESET + "] ";
    public static final String MYSQL = "[" + ANSI_CYAN + "MySQL" + ANSI_RESET + "] ";
    public static final String GUILD = "[" + ANSI_RED + "Guild" + ANSI_RESET + "] ";

    public static final String SYSTEM_NORM = "[" + ANSI_GREEN + "System" + ANSI_RESET + "] ";
    public static final String SYSTEM_ERROR = "[" + ANSI_RED + "System" + ANSI_RESET + "] ";


    public static final String TIME = "[" + ANSI_YELLOW + LocalTime() + ANSI_RESET + "] ";

    public static String LocalTime(){
        LocalTime now = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        String ready = ""+ ANSI_YELLOW + now + ANSI_RESET + "";

        return ready;
    }

}
