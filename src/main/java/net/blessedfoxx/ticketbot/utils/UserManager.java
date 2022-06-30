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
package net.blessedfoxx.ticketbot.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.awt.Color;
import java.util.List;

public class UserManager {

    public static Color getRoleColor(List<Role> roles) {
        for (Role role : roles) {

            if (role.getColor() != null){
                return role.getColor();
            }

        }
        return Color.decode("#E91E63");
    }

    public static void addRoleToUser(Member member, long role) {
        member.getGuild().addRoleToMember(member.getId(), member.getGuild().getRoleById(role)).queue();
    }

    public static boolean isOnline(Member member){
        if (member.getOnlineStatus().equals(OnlineStatus.ONLINE)) {
            return true;
        }else if (member.getOnlineStatus().equals(OnlineStatus.IDLE)) {
            return true;
        }else if (member.getOnlineStatus().equals(OnlineStatus.DO_NOT_DISTURB)) {
            return true;
        }else{
            return false;
        }
    }

    public static void sendPrivatMessage(Member member, String msg) {
        member.getUser().openPrivateChannel().flatMap(privateChannel -> privateChannel.sendMessage(msg)).queue();
    }

    public static void sendPrivatEmbed(Member member, EmbedBuilder builder) {
        member.getUser().openPrivateChannel().flatMap(privateChannel -> privateChannel.sendMessageEmbeds(builder.build())).queue();
    }
}
